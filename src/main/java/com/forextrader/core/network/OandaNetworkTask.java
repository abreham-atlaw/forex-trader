package com.forextrader.core.network;

import com.oanda.v20.ExecuteException;
import com.oanda.v20.RequestException;

public abstract class OandaNetworkTask<T> extends NetworkTask<T> {

	public OandaNetworkTask(int retries){
		super(retries);
	}

	public OandaNetworkTask(){
		super();
	}

	public abstract T executeTask() throws ExecuteException, RequestException;

	@Override
	public T execute() throws NetworkException{
		try{
			return executeTask();
		}
		catch (ExecuteException | RequestException e){
			throw new NetworkException(e.getMessage());
		}
	}



}
