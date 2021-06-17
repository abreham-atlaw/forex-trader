package com.forextrader.webserver;

import com.forextrader.anitidling.AutoCurler;
import com.forextrader.core.AutoTrader;
import com.forextrader.core.Config;
import com.forextrader.core.Log;

import java.io.IOException;
import java.net.*;

public class DummyServer {

	public static void main(String[] args) throws IOException {

		Log.LOGGER.info("[+]Binding ...");
		bind();

		Log.LOGGER.info("[+]Starting Anti-Idling");
		new Thread(() -> {
			AutoCurler curler = new AutoCurler();
			curler.start();
		}).start();

		Log.LOGGER.info("[+]Starting Trader ....");
		AutoTrader trader = new AutoTrader(Config.ACCOUNT_ID);
		trader.start();


	}

	private static void bind() throws IOException {
		Log.LOGGER.info(String.format("Binding to port %s", Config.SERVER_PORT));
		InetAddress inetAddress=InetAddress.getByName(Config.SERVER_ADDRESS);
		ServerSocket socket = new ServerSocket();
		SocketAddress address =new InetSocketAddress(inetAddress, Integer.parseInt(Config.SERVER_PORT));
		socket.bind(address);
	}

}
