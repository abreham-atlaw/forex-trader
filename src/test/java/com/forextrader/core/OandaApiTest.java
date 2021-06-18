package com.forextrader.core;

import com.forextrader.core.network.ApiClient;
import com.forextrader.core.network.NetworkException;
import com.forextrader.core.network.OandaNetworkTask;
import com.oanda.v20.Context;
import com.oanda.v20.ExecuteException;
import com.oanda.v20.RequestException;
import com.oanda.v20.account.AccountSummary;
import com.oanda.v20.account.AccountSummaryResponse;
import org.junit.jupiter.api.Test;


public class OandaApiTest {

	@Test
	public void accountSummaryTest() throws NetworkException, InterruptedException {

		Context context = ApiClient.getContext();
		AccountSummary summary = new OandaNetworkTask<AccountSummaryResponse>() {
			@Override
			public AccountSummaryResponse executeTask() throws ExecuteException, RequestException {
				return context.account.summary(Config.ACCOUNT_ID);
			}
		}.run().getAccount();
		System.out.println(summary);
	}

}