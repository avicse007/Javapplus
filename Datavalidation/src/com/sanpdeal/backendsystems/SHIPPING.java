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

public class SHIPPING {
	//String endpoint="http://20.0.0.96";
	//String p="5050";
	public void getShippingHistoryForOrderSummary(Message message,ReadConfig rc) {
		boolean isWorking = false;
		String url = "/service/shipping/getShippingHistoryForOrderSummary";
		String json = "{\"responseProtocol\":\"PROTOCOL_JSON\",\"requestProtocol\":\"PROTOCOL_JSON\", \"suborderCode\":\"17801759\"}";
		String endpoint=rc.getPropValues("shippingIP");
		String p= rc.getPropValues("shippingPort");
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
			System.out.println("getSellerInventoryPricingForPDP");
		}
		if(!isWorking)
			message.getErrorMessageList().add("Error: The SHIPPING is down now on IP :"+endpoint+" and port "+p);
		else
			System.out.println("SHIPING IS UP");
	}

}
