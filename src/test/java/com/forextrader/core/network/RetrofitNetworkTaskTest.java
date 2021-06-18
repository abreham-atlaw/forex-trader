package com.forextrader.core.network;

import com.forextrader.core.data.Prediction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import retrofit2.Call;

import java.io.IOException;

public class RetrofitNetworkTaskTest {

	private static PredictionApiInterface apiInterface;

	@BeforeAll
	static void setup(){
		apiInterface = ApiClient.getPredictionApiInterface();
	}

	@Test
	public void testSuccessfulTask() throws InterruptedException, NetworkException {
		Prediction prediction = new RetrofitNetworkTask<Prediction>(){

			@Override
			public Call<Prediction> getCall() {
				return apiInterface.fetchPrediction("USD", "EUR");
			}
		}.run();

		Assertions.assertEquals("USD", prediction.getBaseCurrency());
		Assertions.assertEquals("EUR", prediction.getQuoteCurrency());
	}


}
