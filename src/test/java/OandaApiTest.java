import com.forextrader.core.Config;
import com.forextrader.core.network.ApiClient;
import com.oanda.v20.Context;
import com.oanda.v20.ContextBuilder;
import com.oanda.v20.ExecuteException;
import com.oanda.v20.RequestException;
import com.oanda.v20.account.AccountSummary;
import org.junit.jupiter.api.Test;


public class OandaApiTest {

	public static final String TOKEN = "c8de3c1a86b7a9e4d992c2adcc3dd824-bfcf0b5edfd5215a55e12f3037c7695b";
	public static final String URI = "https://api-fxpractice.oanda.com";

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
