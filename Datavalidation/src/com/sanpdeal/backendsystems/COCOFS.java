package com.sanpdeal.backendsystems;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

import com.snapdeal.datavalidator.config.ReadConfig;
import com.snapdeal.datavalidator.util.Message;

public class COCOFS {
	//String endpoint="http://54.251.152.42";
	//String p="10320";
	public void getFulfilmentModel(Message message,ReadConfig rc) {
		boolean isWorking = false;
		String url = "/service/cocofs/getFulfilmentModel";
		String json = "{\"responseProtocol\":\"PROTOCOL_JSON\",\"requestProtocol\":\"PROTOCOL_JSON\",\"sellerSupcPairs\":[{\"supc\":\"SDL039770721\",\"sellerCode\":\"42b5ff\"}]}";
		String endpoint=rc.getPropValues("cocofsIP");
		String p=rc.getPropValues("cocofsPort");
		String finalUrl=endpoint + ":" + p + url;
		HttpPost request = new HttpPost(finalUrl);
		StringEntity entity = null;
		try {
			entity = new StringEntity(json);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		entity.setContentType("application/json");
		request.setEntity(entity);
		HttpResponse response = null;
		HttpClient httpclient = HttpClientBuilder.create().build();
		try {
			response = httpclient.execute(request);
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			isWorking = response.toString().contains("200");
		} catch (Exception e) {
			System.out.println("COCFS is down");
		}
		if(!isWorking)
			message.getErrorMessageList().add("Error: The COCOFS is down now on IP :"+endpoint+" and port "+p);
		else
			System.out.println("COCOFS is up");
	}
}
