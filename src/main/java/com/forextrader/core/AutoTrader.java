package com.forextrader.core;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class AutoTrader extends Trader{

	private final Timer timer = new Timer();


	public AutoTrader(Config.PredictorConfig config) {
		super(config);
	}

	private void tradeRound(List<String> base_currencies, List<String> quote_currencies){

		for(String base_currency: base_currencies){

			for(String quote_currency: quote_currencies){
				try{
					trade(base_currency, quote_currency);
				}
				catch (Exception ex){
					Log.LOGGER.severe(ex.getMessage());
				}
			}

		}

	}

	public void start(){
		Log.LOGGER.info("[+]Starting Auto Trading...");

		long delay = LocalDateTime.now().until(Config.START_DATE, ChronoUnit.MILLIS);
		Log.LOGGER.info(String.format("Trading will start in %d minutes...", delay/(1000*60)));

		timer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				try {
					closeAllOpenTrades();
					tradeRound(config.getBaseCurrencies(), config.getQuoteCurrencies());
				}
				catch (Exception ex){
					Log.LOGGER.severe("tradeRound failed with exception: ");
					Log.LOGGER.severe(ex.getMessage());
				}
			}
		}, delay, Config.TRADE_PERIOD_GAP);
	}

}