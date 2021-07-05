package com.forextrader.core;

public class Main {

	public static void main(String[] args){
		startThreads();
	}

	private static void startThreads(){
		Log.LOGGER.info("[+]Startxing Trading threads...");
		for(Config.PredictorConfig config : Config.PREDICTOR_CONFIGS){
			new Thread(() -> {
				AutoTrader trader = new AutoTrader(config);
				trader.start();
			}).start();
		}
	}
}
