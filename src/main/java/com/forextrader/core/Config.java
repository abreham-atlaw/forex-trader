package com.forextrader.core;

import com.oanda.v20.account.AccountID;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Config {
	public static final LocalDateTime PROGRAM_START_DATE = LocalDateTime.now();

	public static final boolean DEBUG = true;
	public static final int NETWORK_RETRIES = 5;
	public static final int NETWORK_SLEEP = 120000; //IN MICRO SECONDS
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
	public static final String TOKEN = System.getenv("OANDA_TOKEN");
	public static final AccountID TEST_ACCOUNT_ID = new AccountID("101-001-19229086-002");
	public static final AccountID ACCOUNT_ID = new AccountID("101-001-19229086-003");
	public static final String APPLICATION_NAME = "Trader";

	public static final String PREDICTION_URL = "https://fpredictor.herokuapp.com";

	public static final String BASE_CURRENCY = "USD";
	public static final List<String> CURRENCIES = new ArrayList<>(Arrays.asList(
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
	));

	//WebServer Configs
	public static final String SERVER_PORT = (System.getenv("PORT"));
	public static final String SERVER_ADDRESS = "0.0.0.0";

	//AntiIdling Configs
	public static final String SERVER_URL = "https://forex-trader.herokuapp.com/";
	public static final int CURL_GAP = 30*1000;

}
