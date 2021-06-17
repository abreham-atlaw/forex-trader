package com.forextrader.core.network;

import com.forextrader.core.data.Prediction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import retrofit2.Call;

import java.io.IOException;

public class NetworkTaskTest {

	private static PredictionApiInterface apiInterface;

	@BeforeAll
	static void setup(){
		apiInterface = ApiClient.getPredictionApiInterface();
	}

	@Test
	public void testSuccessfulTask() throws IOException, InterruptedException{
		Prediction prediction = new NetworkTask<Prediction>(){

			@Override
			public Call<Prediction> getCall() {
				return apiInterface.fetchPrediction("USD", "EUR");
			}
		}.execute();

		Assertions.assertEquals("USD", prediction.getBaseCurrency());
		Assertions.assertEquals("EUR", prediction.getQuoteCurrency());
	}


}
