package com.forextrader.core.network;

import com.forextrader.core.data.Prediction;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import java.util.List;

public interface PredictionApiInterface {

	@GET("prediction/")
	Call<Prediction> fetchPrediction(@Query("base_currency") String baseCurrency, @Query("quote_currency") String quoteCurrency);

	@GET("prediction/")
	Call<List<Prediction>> fetchAllPredictions();

}
