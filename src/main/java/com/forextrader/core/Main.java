package com.forextrader.core;

import java.util.List;

public class Main {

	public static void main(String[] args){
		startThreads(Config.PREDICTOR_CONFIGS);
	}

	public static void startThreads(List<Config.PredictorConfig> configs){
		Log.LOGGER.info("[+]Starting Trading threads...");
		for(Config.PredictorConfig config : configs){
			new Thread(() -> {
				AutoTrader trader = new AutoTrader(config);
				trader.start();
			}).start();
		}
	}
}
