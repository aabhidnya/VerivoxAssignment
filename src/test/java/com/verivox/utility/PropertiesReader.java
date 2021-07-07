package com.verivox.utility;

import java.io.InputStream;
import java.util.Properties;

public class PropertiesReader {
	String fileName = "config.properties";
    public String getValue(String key) throws Throwable {

        	ClassLoader classLoader = getClass().getClassLoader();
            InputStream inputStream = classLoader.getResourceAsStream(fileName);
            if (inputStream == null) {
                throw new IllegalArgumentException("file not found! " + fileName);
            } else {
            	Properties properties = new Properties();
                properties.load(inputStream);
                return properties.getProperty(key);
            }
    }
}
