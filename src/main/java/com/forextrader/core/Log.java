package com.forextrader.core;

import java.io.IOException;
import java.util.logging.*;

public class Log {

	private static FileHandler logFileHandler;
	private static SimpleFormatter formatter;

	public static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	private static void disableConsoleLog(){
		Logger globalLogger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
		Handler[] handlers = globalLogger.getHandlers();
		for(Handler handler : handlers) {
			globalLogger.removeHandler(handler);
		}
	}

	public static void setup(boolean console, String filePath) throws IOException {
		Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

		if(!console)
			disableConsoleLog();

		logger.setLevel(Level.ALL);
		logFileHandler = new FileHandler(filePath);
		formatter = new SimpleFormatter();
		logFileHandler.setFormatter(formatter);
		logger.addHandler(logFileHandler);

	}

	public static void setup(boolean console) throws IOException{
		setup(console, Config.LOG_PATH);
	}

	public static void setup(String filePath) throws IOException{
		setup(Config.CONSOLE_LOG, filePath);
	}

	public static void setup() throws IOException{
		setup(Config.CONSOLE_LOG, Config.LOG_PATH);
	}


}
