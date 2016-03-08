package com.snapdeal.datavalidator.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ReadConfig {

	private String filename = "property.properties";
	private String baseDir;
	public ReadConfig(String baseDir){
		this.baseDir=baseDir;
	}

	public String getPropValues(String property) {
		String result = "";
		InputStream inputStream = null;
		try {
			Properties prop = new Properties();
			inputStream = new FileInputStream(baseDir + filename);
			prop.load(inputStream);
			result = prop.getProperty(property);
		} catch (Exception e) {
			System.out.println("Exception: " + e);
		} finally {
			try {
				inputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
}
