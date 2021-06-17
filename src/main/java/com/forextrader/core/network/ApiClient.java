package com.forextrader.core.network;

import com.forextrader.core.Config;
import com.oanda.v20.Context;
import com.oanda.v20.ContextBuilder;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ApiClient {

	private static Context context = null;
	private static PredictionApiInterface apiInterface = null;

	public static Retrofit getClient(boolean debug){

		Retrofit.Builder builder = new Retrofit.Builder()
				.baseUrl(Config.PREDICTION_URL)
				.addConverterFactory(GsonConverterFactory.create());

		if(debug)
			builder.client(
					new OkHttpClient.Builder().addInterceptor(new HttpLoggingInterceptor()).build()
			);

		return builder.build();

	}

	public static Retrofit getClient(){
		return getClient(Config.DEBUG);
	}

	public static PredictionApiInterface getPredictionApiInterface(){
		if(apiInterface == null){
			apiInterface = getClient().create(PredictionApiInterface.class);
		}
		return apiInterface;
	}

	public static Context getContext(){
		if(context == null){
			context = new ContextBuilder(Config.TRADING_URL)
					.setToken(Config.TOKEN)
					.setApplication(Config.APPLICATION_NAME)
					.build();
		}
		return context;
	}


}
