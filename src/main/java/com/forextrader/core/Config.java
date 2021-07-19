package com.forextrader.core;

import com.oanda.v20.account.AccountID;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Config {

	public static final LocalDateTime PROGRAM_START_DATE = LocalDateTime.now();

	public static final boolean DEBUG = false;
	public static final int NETWORK_RETRIES = 10;
	public static final int NETWORK_SLEEP = 3*60*1000;
	public static final String LOG_PATH = "./trader.log";
	public static final boolean CONSOLE_LOG = true;
	public static final int TRADE_PERIOD_GAP = 24*60*60*1000;
	public static final LocalDateTime START_DATE = LocalDateTime.of(
			PROGRAM_START_DATE.getYear(),
			PROGRAM_START_DATE.getMonth(),
			PROGRAM_START_DATE.getDayOfMonth(),
			0,
			0,
			0
	).plusDays(1);

	public static final String TRADING_URL = "https://api-fxpractice.oanda.com";
	public static final String TOKEN = "OANDA_TOKEN"; //UNSAFE. Use an environment variable like System.getenv("OANDA_TOKEN");
	public static final String APPLICATION_NAME = "Trader";

	public static final String PREDICTION_URL = "https://fpredictor.herokuapp.com";

	public static final List<PredictorConfig> PREDICTOR_CONFIGS = Arrays.asList(
/**			new PredictorConfig(
					"0",
					new AccountID("101-001-19229086-003"),
					Collections.singletonList("USD"),
					Arrays.asList(
							"AUD",  // Australia -
							//	"BRL",  // Brazil
							"CAD",  // Canada -
							"CNH",  // China -
							"DKK",  // Denmark -
							"EUR",  // Euro -
							"HKD",  // Hong Kong -
							"JPY",  // Japan -
							"NZD",  // New Zealand -
							"NOK",  // Norway -
							"SGD",  // Singapore -
							"ZAR",  // South Africa -
							//	"KRW",  // South Korea
							"SEK",  // Sweden -
							"CHF",  // Switzerland -
							//	"TWD",  // Taiwan
							"GBP"   // United Kingdom -
					)
			),*/

			new PredictorConfig(
					"1",
					new AccountID("101-001-19229086-004"),
					Collections.singletonList("USD"),
					Collections.singletonList("GBP")
			),

			new PredictorConfig(
					"2",
					new AccountID("101-001-19229086-005"),
					Collections.singletonList("USD"),
					Collections.singletonList("GBP")
			),

			new PredictorConfig(
					"3",
					new AccountID("101-001-19229086-006"),
					Collections.singletonList("USD"),
					Collections.singletonList("GBP")
			)
	);

	public static final List<PredictorConfig> TEST_PREDICTOR_CONFIGS = Arrays.asList(
		new PredictorConfig(
					"0",
					new AccountID("101-001-19229086-001"),
					Collections.singletonList("USD"),
					Arrays.asList(
							"AUD",  // Australia -
							//	"BRL",  // Brazil
							"CAD",  // Canada -
							"CNH",  // China -
							"DKK",  // Denmark -
							"EUR",  // Euro -
							"HKD",  // Hong Kong -
							"JPY",  // Japan -
							"NZD",  // New Zealand -
							"NOK",  // Norway -
							"SGD",  // Singapore -
							"ZAR",  // South Africa -
							//	"KRW",  // South Korea
							"SEK",  // Sweden -
							"CHF",  // Switzerland -
							//	"TWD",  // Taiwan
							"GBP"   // United Kingdom -
					)
			),

			new PredictorConfig(
					"1",
					new AccountID("101-001-19229086-002"),
					Collections.singletonList("GBP"),
					Collections.singletonList("USD")
			)
	);

	static class PredictorConfig {

		private final String id;
		private final List<String> base_currencies;
		private final List<String> quote_currencies;
		private final AccountID accountID;

		public PredictorConfig(
				String id,
				AccountID accountID,
				List<String> base_currencies,
				List<String> quote_currencies
		){

			this.id = id;
			this.accountID = accountID;
			this.base_currencies = base_currencies;
			this.quote_currencies = quote_currencies;

		}

		public String getId() {
			return id;
		}

		public List<String> getBaseCurrencies() {
			return base_currencies;
		}

		public List<String> getQuoteCurrencies() {
			return quote_currencies;
		}

		public AccountID getAccountID() {
			return accountID;
		}
	}

}
