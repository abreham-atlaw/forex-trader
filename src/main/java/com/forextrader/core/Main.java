package com.forextrader.core;

public class Main {

	public static void main(String[] args){

		AutoTrader trader = new AutoTrader(Config.TEST_ACCOUNT_ID);
		trader.start();

	}
}