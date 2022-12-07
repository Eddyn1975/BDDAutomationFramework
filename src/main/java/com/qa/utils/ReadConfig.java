package com.qa.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ReadConfig {
	Properties property;
	FileInputStream fis;

	public Properties init_prop() {
		File src = new File(("./src/test/resources/com/qa/config/config.properties"));
		try {
			fis = new FileInputStream(src);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		property = new Properties();
		try {
			property.load(fis);
		} catch (IOException e) {
			e.printStackTrace();
		}
        return property;
	}

	public String getBaseUrl() {
		String url = property.getProperty("baseUrl");
		return url;
	}
	public String getBrowser() {
		String url = property.getProperty("Browser");
		return url;
	}
}
