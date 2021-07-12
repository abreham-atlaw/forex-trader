package com.forextrader.core.network;

import com.forextrader.core.Log;
import org.junit.jupiter.api.Test;

public class NetworkTaskTest {

	@Test
	void networkTest() throws NetworkException, InterruptedException {
		new NetworkTask<Void>(){

			@Override
			public Void execute() throws NetworkException {
				Log.LOGGER.info("Throwing Exception");
				throw new NetworkException();
			}
		}.run();
	}

}
