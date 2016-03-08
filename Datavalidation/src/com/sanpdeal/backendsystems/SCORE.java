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

public class SCORE {
	//String endpoint="http://54.251.152.42";
	//String p="10070";
	public void getSellerWiseDeliveryInfo(Message message,ReadConfig rc) {
		boolean isWorking = false;
		String url = "/service/score/getSellerWiseDeliveryInfo";
		String json = "{\"responseProtocol\":\"PROTOCOL_JSON\",\"requestProtocol\":\"PROTOCOL_JSON\",\"useCutOffBuffer\":\"false\",\"additionalChargesRequired\":\"true\",\"calculateDeliveryDate\":\"true\",\"supc\":\"SDL287280643\",\"pincode\":\"110020\",\"productInfoSro\":{  \"categoryUrl\":\"shbtesting-testsubthree\",\"brandName\":\"Adidas\",\"make2Order\":\"false\"},\"sellerInfo\":[{\"sellerCode\":\"S467d4\",\"sellingPrice\":\"444.0\",\"fulfilmentModel\":\"DROPSHIP\",\"fcCodes\":null,\"shippingCharge\":\"0\"}]}";
		String endpoint=rc.getPropValues("scoreIP");
		String p=rc.getPropValues("scorePort");
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
			System.out.println("SCORE is down");
		}
		if(!isWorking)
			message.getErrorMessageList().add("Error: The SCORE is down now on IP :"+endpoint+" and port "+p);
		else
			System.out.println("SCORE is UP");
	}

}
