package com.forextrader.core.network;

import com.forextrader.core.Config;
import com.forextrader.core.Log;

public abstract class NetworkTask<T> {

	private int retries;

	public NetworkTask(int retries){
		this.retries = retries;
	}

	public NetworkTask(){
		this.retries = Config.NETWORK_RETRIES;
	}

	public abstract T execute() throws NetworkException;

	public T run() throws NetworkException, InterruptedException{
		try{
			return execute();
		}
		catch (NetworkException e){
			Log.LOGGER.warning(e.getMessage());
			if(retries > 0){
				retries--;
				Log.LOGGER.warning(String.format("Network Failure. %d tries left. Sleeping", retries));
				Thread.sleep(Config.NETWORK_SLEEP);
				return run();
			}
			Log.LOGGER.severe("Network Failure Quitting Tries");
			throw e;
		}
	}
}
