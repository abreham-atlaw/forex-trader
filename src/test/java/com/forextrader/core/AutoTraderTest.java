package com.forextrader.core;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class AutoTraderTest {

	public static AutoTrader trader;

	@BeforeAll
	static void setup(){
		trader = new AutoTrader(Config.TEST_PREDICTOR_CONFIGS.get(1));
	}

	@Test
	void testNormalRun(){
		trader.start();
	}

}
