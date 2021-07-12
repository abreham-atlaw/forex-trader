package com.forextrader.core;

import org.junit.jupiter.api.Test;

public class MainTest {

	@Test
	void startThreadsTest(){
		Main.startThreads(Config.TEST_PREDICTOR_CONFIGS);
	}

}
