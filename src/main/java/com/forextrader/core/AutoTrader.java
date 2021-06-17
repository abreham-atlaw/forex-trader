package com.forextrader.core;

import com.forextrader.core.exceptions.InstrumentUnavailableException;
import com.oanda.v20.ExecuteException;
import com.oanda.v20.RequestException;
import com.oanda.v20.account.AccountID;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class AutoTrader extends Trader{

	private final Timer timer = new Timer();

	public AutoTrader(AccountID accountId) {
		super(accountId);
	}

	private void tradeRound(String base_currency, List<String> currencies){
		for(String currency : currencies){
			try{
				trade(base_currency, currency);
			}
			catch (Exception e) {
				Log.LOGGER.severe(e.getMessage());
			}
		}
	}

	public void start(String base_currency, List<String> currencies){
		Log.LOGGER.info("[+]Starting Auto Trading...");

		long delay = LocalDateTime.now().until(Config.START_DATE, ChronoUnit.MILLIS);
		Log.LOGGER.info(String.format("Trading will start in %d minutes...", delay/(1000*60)));

		timer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				try {
					closeAllOpenTrades();
					tradeRound(base_currency, currencies);
				}
				catch (Exception ex){
					Log.LOGGER.severe("tradeRound failed with exception: ");
					Log.LOGGER.severe(ex.getMessage());
				}
			}
		}, delay, Config.TRADE_PERIOD_GAP);
	}

	public void start(){
		this.start(Config.BASE_CURRENCY, Config.CURRENCIES);
	}

}