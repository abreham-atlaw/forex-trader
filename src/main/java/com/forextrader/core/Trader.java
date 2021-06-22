package com.forextrader.core;

import com.forextrader.core.data.Prediction;
import com.forextrader.core.exceptions.InstrumentUnavailableException;
import com.forextrader.core.network.*;
import com.oanda.v20.Context;
import com.oanda.v20.ExecuteException;
import com.oanda.v20.RequestException;
import com.oanda.v20.account.AccountID;
import com.oanda.v20.order.MarketOrderRequest;
import com.oanda.v20.order.OrderCreateRequest;
import com.oanda.v20.order.OrderCreateResponse;
import com.oanda.v20.primitives.Instrument;
import com.oanda.v20.trade.Trade;
import com.oanda.v20.trade.TradeCloseRequest;
import com.oanda.v20.trade.TradeCloseResponse;
import com.oanda.v20.trade.TradeSpecifier;
import com.oanda.v20.transaction.TransactionID;
import kotlin.Pair;
import retrofit2.Call;

import java.util.List;

public class Trader {

	final PredictionApiInterface apiInterface;
	final AccountID accountId;
	final Context context;

	public Trader(AccountID accountId){
		this.accountId = accountId;
		this.apiInterface = ApiClient.getPredictionApiInterface();
		this.context = ApiClient.getContext();
	}

	public Prediction getPrediction(String baseCurrency, String quoteCurrency) throws NetworkException, InterruptedException {
		return new RetrofitNetworkTask<Prediction>(){

			@Override
			public Call<Prediction> getCall() {
				Log.LOGGER.info(String.format("[+]Getting Predictions for %s/%s ...", baseCurrency, quoteCurrency));
				return apiInterface.fetchPrediction(baseCurrency, quoteCurrency);
			}


		}.run();
	}

	public TradeAction getAction(String baseCurrency, String quoteCurrency) throws InterruptedException, NetworkException {
		Prediction prediction = getPrediction(baseCurrency, quoteCurrency);

		if(prediction.getValue() > 0)
			return TradeAction.BUY;
		else if(prediction.getValue() < 0)
			return TradeAction.SELL;
		return TradeAction.STALL;
	}

	private Pair<String, TradeAction> getProperInstrumentActionPair(String baseCurrency, String quoteCurrency, TradeAction action) throws InterruptedException, NetworkException, InstrumentUnavailableException {
		Log.LOGGER.info(String.format("[+]Getting Proper Instrument-Action Pair for %s/%s %s", baseCurrency, quoteCurrency, action.toString()));
		String standardForm = String.format("%s_%s", baseCurrency, quoteCurrency);
		String reverseForm = String.format("%s_%s", quoteCurrency, baseCurrency);
		if(action == TradeAction.STALL)
			return new Pair<>(standardForm, action);
		List<Instrument> availableInstruments = new OandaNetworkTask<List<Instrument>>(){

			@Override
			public List<Instrument> executeTask() throws ExecuteException, RequestException {
				return context.account.instruments(Config.ACCOUNT_ID).getInstruments();
			}
		}.run();



		for(Instrument instrument : availableInstruments){
			if(instrument.getName().toString().equals(standardForm))
				return new Pair<>(standardForm, action);
			if(instrument.getName().toString().equals(reverseForm)){
				if(action == TradeAction.BUY)
					action = TradeAction.SELL;
				else if(action == TradeAction.SELL)
					action = TradeAction.BUY;
				return new Pair<>(reverseForm, action);
			}
		}

		Log.LOGGER.warning(String.format("[-]Instrument Not Available: %s/%s", baseCurrency, quoteCurrency));

		throw new InstrumentUnavailableException();
	}

	public TransactionID makeOrder(String baseCurrency, String quoteCurrency, TradeAction action) throws InstrumentUnavailableException, InterruptedException, NetworkException {
		Log.LOGGER.info(String.format("[+]Making Order %s/%s with action %s", baseCurrency, quoteCurrency, action.toString()));
		if(action == TradeAction.STALL)
			return null;

		OrderCreateRequest request = new OrderCreateRequest(accountId);

		Pair<String, TradeAction> instrumentActionPair = getProperInstrumentActionPair(baseCurrency, quoteCurrency, action);

		int units = getUnits();
		if(instrumentActionPair.getSecond() == TradeAction.SELL)
			units *= -1;

		request.setOrder(new MarketOrderRequest()
				.setUnits(units)
				.setInstrument(instrumentActionPair.getFirst())
		);

		return new OandaNetworkTask<OrderCreateResponse>(){

			@Override
			public OrderCreateResponse executeTask() throws ExecuteException, RequestException {
				return context.order.create(request);
			}
		}.run().getOrderFillTransaction().getId();

	}

	public void closeTrade(TransactionID transactionId) throws NetworkException, InterruptedException {
		Log.LOGGER.info(String.format("[+]Closing Trade with Transaction id %s...", transactionId.toString()));
		TradeCloseResponse response = new OandaNetworkTask<TradeCloseResponse>(){

			@Override
			public TradeCloseResponse executeTask() throws ExecuteException, RequestException {
				return context.trade.close(new TradeCloseRequest(accountId, new TradeSpecifier(transactionId.toString())));
			}
		}.run();


		//TODO: VALIDATE THAT THE TRADE IS CLOSED.
	}

	public void closeAllOpenTrades() throws NetworkException, InterruptedException {
		Log.LOGGER.info("[+]Closing all trades...");


		List<Trade> openTrades = new OandaNetworkTask<List<Trade>>(){

			@Override
			public List<Trade> executeTask() throws ExecuteException, RequestException {
				return context.trade.listOpen(Config.ACCOUNT_ID).getTrades();
			}
		}.run();


		for(Trade trade : openTrades){
			closeTrade(new TransactionID(trade.getId().toString()));
		}
	}

	public void trade(String baseCurrency, String quoteCurrency) throws InstrumentUnavailableException, NetworkException, InterruptedException {
		Log.LOGGER.info(String.format("[+]Starting trade %s/%s", baseCurrency, quoteCurrency));
		TradeAction action = getAction(baseCurrency, quoteCurrency);

		if(action == TradeAction.STALL)
			return;

		makeOrder(baseCurrency, quoteCurrency, action);
	}

	public int getUnits(){
		Log.LOGGER.info("Getting Units...");
		return (5000/Config.CURRENCIES.size());
	}

	public enum TradeAction {

		SELL,
		BUY,
		STALL

	}


}
