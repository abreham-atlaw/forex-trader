import com.forextrader.core.Config;
import com.forextrader.core.network.ApiClient;
import com.oanda.v20.Context;
import com.oanda.v20.ExecuteException;
import com.oanda.v20.RequestException;
import com.oanda.v20.account.AccountID;
import com.oanda.v20.account.AccountSummary;
import com.oanda.v20.primitives.Instrument;

import java.util.List;
import java.util.function.Consumer;

public class Test {

	public static void main(String[] args){

		Context context = ApiClient.getContext();

		try {
			List<Instrument> instruments = context.account.instruments(Config.ACCOUNT_ID).getInstruments();
			instruments.forEach(instrument -> {
				System.out.println(instrument.getName().toString());
			});
		}
		 catch (ExecuteException | RequestException e) {
			e.printStackTrace();
		}
	}

}
