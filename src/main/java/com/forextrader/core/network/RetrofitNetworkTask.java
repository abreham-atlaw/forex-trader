package com.forextrader.core.network;

import retrofit2.Call;

import java.io.IOException;

abstract public class RetrofitNetworkTask<T> extends NetworkTask<T> {


	public RetrofitNetworkTask(int retries){
		super(retries);
	}

	public RetrofitNetworkTask(){
		super();
	}

	public abstract Call<T> getCall();

	@Override
	public T execute() throws NetworkException{
		try {
			return getCall().execute().body();
		}
		catch (IOException e){
			throw new NetworkException(e.getMessage());
		}
	}

}
