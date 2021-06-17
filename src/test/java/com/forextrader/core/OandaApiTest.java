package com.forextrader.core;

import com.forextrader.core.Config;
import com.forextrader.core.network.ApiClient;
import com.oanda.v20.Context;
import com.oanda.v20.ExecuteException;
import com.oanda.v20.RequestException;
import com.oanda.v20.account.AccountSummary;
import org.junit.jupiter.api.Test;


public class OandaApiTest {

	@Test
	public void accountSummaryTest(){

		Context context = ApiClient.getContext();

		try{
			AccountSummary summary = context.account.summary(Config.ACCOUNT_ID).getAccount();
			System.out.println(summary);
		}
		catch (ExecuteException | RequestException e) {
			e.printStackTrace();
		}

	}

}
