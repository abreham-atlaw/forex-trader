package com.forextrader.core;

import com.forextrader.core.data.Prediction;
import com.forextrader.core.exceptions.InstrumentUnavailableException;
import com.forextrader.core.network.NetworkException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


public class TraderTest {

	private static Trader trader;
	private static final String BASE_CURRENCY = "USD";
	private static final String QUOTE_CURRENCY = "CNH";


	@BeforeAll
	static void setup(){
		trader = new Trader(Config.TEST_PREDICTOR_CONFIGS.get(0));
	}

	@Test
	void testGetPrediction() throws InterruptedException, NetworkException {
		Prediction prediction = trader.getPrediction(BASE_CURRENCY, QUOTE_CURRENCY);
		Assertions.assertNotNull(prediction);
	}

	@Test
	void testMakeOrder() throws InstrumentUnavailableException, InterruptedException, NetworkException {
		trader.makeOrder(BASE_CURRENCY, QUOTE_CURRENCY, Trader.TradeAction.BUY);
	}

	@Test
	void testTrade() throws InterruptedException, InstrumentUnavailableException, NetworkException {
		trader.trade(BASE_CURRENCY, QUOTE_CURRENCY);
	}

	@Test
	void testCloseAllOrders() throws NetworkException, InterruptedException {
		trader.closeAllOpenTrades();
	}



}
