package com.forextrader.core.antiidling;

import com.forextrader.anitidling.AutoCurler;
import com.forextrader.core.Config;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class AutoCurlerTest {

	private static AutoCurler curler;

	@BeforeAll
	public static void setup(){
		curler = new AutoCurler();
	}

	@Test
	public void testCurl() throws IOException {
		curler.curl(Config.SERVER_URL);
	}

	@Test
	public void testAutoCurl(){
		curler.start(Config.SERVER_URL, 30*1000);
	}



}
