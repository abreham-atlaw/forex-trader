package com.forextrader.core.network;

public class NetworkException extends Exception{

	public NetworkException(String msg){
		super(msg);
	}

	public NetworkException(){
		super();
	}
}
