package org.loy.log4j;

import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class HelloExample{

	public static void main(String[] args) {
		HelloExample obj = new HelloExample();
		obj.runMe("mkyong");
	}

	private void runMe(String parameter){

        try {
            Properties p = new Properties();
            p.load(HelloExample.class.getClassLoader().getResourceAsStream("log4j.properties"));
            p.put("username", "LeonLong");
            PropertyConfigurator.configure(p);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Logger resultLog = Logger.getLogger("configDiffLog");
        Logger logger = Logger.getLogger(HelloExample.class);

		if(logger.isDebugEnabled()){
			logger.debug("This is debug : " + parameter);
		}
		if(logger.isInfoEnabled()){
			logger.info("This is info : " + parameter);
		}
		logger.warn("This is warn : " + parameter);
		logger.error("This is error : " + parameter);
		logger.fatal("This is fatal : " + parameter);
	}

}