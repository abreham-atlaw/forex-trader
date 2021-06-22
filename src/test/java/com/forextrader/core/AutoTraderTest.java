package com.forextrader.core;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class AutoTraderTest {

	public static AutoTrader trader;

	@BeforeAll
	static void setup(){
		trader = new AutoTrader(Config.TEST_ACCOUNT_ID);
	}

	@Test
	void testNormalRun(){
		trader.start();
	}

}
