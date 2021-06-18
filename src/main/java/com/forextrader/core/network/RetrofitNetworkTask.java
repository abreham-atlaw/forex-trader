package com.forextrader.core.network;

import com.forextrader.core.Config;
import com.forextrader.core.Log;
import retrofit2.Call;

import java.io.IOException;

abstract public class RetrofitNetworkTask<T> {

	private int retries;

	public RetrofitNetworkTask(int retries){
		this.retries = retries;
	}

	public RetrofitNetworkTask(){
		this.retries = Config.NETWORK_RETRIES;
	}

	public abstract Call<T> getCall();

	public T execute() throws IOException, InterruptedException{
		try{
			return getCall().execute().body();
		}
		catch (IOException e) {
			Log.LOGGER.warning(e.getMessage());
			if(retries > 0){
				retries--;
				Log.LOGGER.warning(String.format("Network Failure. %d tries left. Sleeping", retries));
				Thread.sleep(Config.NETWORK_SLEEP);
				return execute();
			}
			Log.LOGGER.severe("Network Failure Quiting Tries.");
			throw e;
		}
	}

}
