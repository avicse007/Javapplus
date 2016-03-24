package com.snapdeal.ite4helthcheck.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
/**
 * 
 * @author singh.avinash
 *
 */
public class ReadProperty {
	String filePath = "/src/main/java/com/snapdeal/ite4helthcheck/util/";
	FileInputStream fis = null;
	String projectName = "ITE4HealthCheck";

	public void initilizeFileInputStream() {
		try {
			File f = new File(System.getProperty("user.dir") + filePath + "api.properties");
			// Made changes
			if (!f.exists())
				f = new File(System.getProperty("user.dir") + "/"+projectName + filePath + "api.properties");
			fis = new FileInputStream(f);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public String readApiJson(String apinameJson) {
		String jsonbody = "";
		try {
			initilizeFileInputStream();
			Properties property = new Properties();
			property.load(fis);
			jsonbody = property.getProperty(apinameJson);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return jsonbody;
	}

	public String readApiUrl(String apinameUrl, String ApiPort, String endpoint) {
		String commonendpoint = "";
		String url = "";
		String port = "";
		String fullApiUrl = "";
		try {
			initilizeFileInputStream();
			Properties property = new Properties();
			property.load(fis);
			commonendpoint = property.getProperty(endpoint);
			port = property.getProperty(ApiPort);
			url = property.getProperty(apinameUrl);
			fullApiUrl = commonendpoint + ":" + port + url;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return fullApiUrl;
	}

	public String readProperty(String healthCheckUrl) {
		String fullUrl = "";
		try {
			initilizeFileInputStream();
			Properties property = new Properties();
			property.load(fis);
			fullUrl = property.getProperty(healthCheckUrl);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return fullUrl;
	}

	public static void main(String avi[]) {
		ReadProperty rp = new ReadProperty();
		System.out.println(rp.readApiJson("BOSS_getAvailableFacilities_Json"));
		System.out.println(rp.readApiUrl("BOSS_getAvailableFacilities_URL", "MobAPIPort", "commonEndPoint"));

	}

}
