package com.forextrader.core.network;


import com.forextrader.core.data.Prediction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class ApiInterfaceTest {

	private static PredictionApiInterface apiInterface;
	private static final String BASE_CURRENCY = "GBP";
	private static final String QUOTE_CURRENCY = "USD";
	private static final String MODEL_ID = "1";

	@BeforeAll
	static void setup(){
		apiInterface = ApiClient.getPredictionApiInterface();
	}


	@Test
	public void testFetchPrediction(){


		Prediction prediction = null;
		try {
			prediction = apiInterface.fetchPrediction(BASE_CURRENCY, QUOTE_CURRENCY, MODEL_ID).execute().body();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Assertions.assertNotNull(prediction);
		Assertions.assertEquals(BASE_CURRENCY, prediction.getBaseCurrency());
		Assertions.assertEquals(QUOTE_CURRENCY, prediction.getQuoteCurrency());
	}





}
