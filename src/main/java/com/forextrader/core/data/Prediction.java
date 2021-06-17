package com.forextrader.core.data;

import com.google.gson.annotations.SerializedName;

public class Prediction {

	@SerializedName("base_currency")
	private final String baseCurrency;

	@SerializedName("quote_currency")
	private final String quoteCurrency;

	@SerializedName("value")
	private final double value; //This Value should be percentage change

	public Prediction(String baseCurrency, String quoteCurrency, double value){
		this.baseCurrency = baseCurrency;
		this.quoteCurrency = quoteCurrency;
		this.value = value;
	}

	public String getBaseCurrency() {
		return baseCurrency;
	}

	public String getQuoteCurrency() {
		return quoteCurrency;
	}

	public double getValue() {
		return value;
	}

}
