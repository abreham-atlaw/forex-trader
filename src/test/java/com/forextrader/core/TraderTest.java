package com.forextrader.core;

import com.forextrader.core.data.Prediction;
import com.forextrader.core.exceptions.InstrumentUnavailableException;
import com.oanda.v20.ExecuteException;
import com.oanda.v20.RequestException;
import com.oanda.v20.transaction.TransactionID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class TraderTest {

	private static Trader trader;
	private static final String BASE_CURRENCY = "USD";
	private static final String QUOTE_CURRENCY = "CNH";


	@BeforeAll
	static void setup(){
		trader = new Trader(Config.ACCOUNT_ID);
	}

	@Test
	void testGetPrediction() throws IOException, InterruptedException{
		Prediction prediction = trader.getPrediction(BASE_CURRENCY, QUOTE_CURRENCY);
		Assertions.assertEquals(BASE_CURRENCY, prediction.getBaseCurrency());
	}

	@Test
	void testGetAction() throws IOException, InterruptedException{
		Trader.TradeAction action = trader.getAction(BASE_CURRENCY, QUOTE_CURRENCY);
		Assertions.assertEquals(Trader.TradeAction.SELL, action);
	}

	@Test
	void testMakeOrder() throws ExecuteException, RequestException, InstrumentUnavailableException {
		TransactionID id = trader.makeOrder(BASE_CURRENCY, QUOTE_CURRENCY, Trader.TradeAction.BUY);
	}

	@Test
	void testTrade() throws ExecuteException, IOException, RequestException, InterruptedException, InstrumentUnavailableException {
		trader.trade(BASE_CURRENCY, QUOTE_CURRENCY);
	}

	@Test
	void testCloseAllOrders() throws ExecuteException, RequestException {
		trader.closeAllOpenTrades();
	}



}
