package com.forextrader.anitidling;

import com.forextrader.core.Config;
import com.forextrader.core.Log;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class AutoCurler {

	private final Timer timer = new Timer();

	public AutoCurler(){

	}

	public void curl(String url) throws IOException {
		Log.LOGGER.info(String.format("[+]Curling %s", url));
		Runtime.getRuntime().exec(String.format("curl %s", url));
	}

	public void start(String url, int gap){
		Log.LOGGER.info("[+]Starting AutoCurler ...");
		timer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				try {
					curl(url);
				}
				catch (Exception ex){
					Log.LOGGER.severe(ex.getMessage());
				}
			}
		}, 5, gap);
	}

	public void start(){
		start(Config.SERVER_URL, Config.CURL_GAP);
	}

}
