package com.snapdeal.ite4healthcheck.backendsystem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.snapdeal.ite4helthcheck.util.ReadProperty;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

/**
 * 
 * @author singh.avinash
 *
 */
public class BackendSystem {
	ReadProperty readPro;
	
	public boolean systemHelthcheck(String healthCheckUrl,Message message){
		boolean islive=false;
		String fullurl=readPro.readProperty(healthCheckUrl);
		//System.out.println("System failed is "+healthCheckUrl);
		//System.out.println("Full url in systemHelthcheck is :"+fullurl);
		try {
			Client client = Client.create();

			WebResource webResource = client
			   .resource(fullurl);
			
			////20.0.1.222:30010
			ClientResponse response = webResource.accept("application/json")
	                   .get(ClientResponse.class);
			String output3 = response.getEntity(String.class);
			if (response.getStatus() != 200){
			islive=false;	
			//message.getSystemErrorList().add(systemport.split("_")[0]+" is not working on port :"+systemport +"and at end point : "+commonEndpoint);
			
			//System.out.println("Current response of the system is :"+output3);
			}
			if (response.getStatus() == 200){
				islive=true;	
				message.getSystemErrorList().add("System >>"+healthCheckUrl+">>>healthcheckUrl >>"+fullurl);
				//System.out.println("Current response of the system is :"+output3);
				//System.out.println(systemport.split("_")[0]+" is not working on port :"+systemport +"and at end point : "+commonEndpoint);
				}
		 
	}catch(Exception e){
		islive=false;
		//e.printStackTrace();
	}
		if(!islive)
			message.getSystemErrorList().add("System >>"+healthCheckUrl+">>>healthcheckUrl >>"+fullurl);
		return islive;
	}
	
	public boolean apiCheck(String apinameJson,String apinameUrl,Message message){
		boolean isApiResponse=false;
		String json=readPro.readProperty(apinameJson);
		String fullUrl=readPro.readProperty(apinameUrl);
		//String json= "{\"responseProtocol\":\"PROTOCOL_JSON\",\"requestProtocol\":\"PROTOCOL_JSON\",\"supc\":\"SDL11665012412\",\"locale\":\"en\",\"deviceType\":\"WEB\",\"zone\":\"NO_ZONE\"}";
		  //String json= "{\"responseProtocol\":\"PROTOCOL_JSON\",\"requestProtocol\":\"PROTOCOL_JSON\",\"sellerSupcPairs\":[{\"supc\":\"SDL039770721\",\"sellerCode\":\"42b5ff\"}]}";
		//String fullUrl="http://10.41.92.43:10320/service/cocofs/getFulfilmentModelBySellerSupcSubcat";
		try {
			Client client = Client.create();

			WebResource webResource = client
			   .resource(fullUrl);
			ClientResponse response=webResource.type("application/json").post(ClientResponse.class,json);
			String output3 = response.getEntity(String.class);
			System.out.println("API Response is "+output3);
			if (response.getStatus() != 200){
				isApiResponse=false;
				message.getAPIErrorList().add("API Failed is>>>"+apinameUrl+">>>at URL>>>"+fullUrl+">>>With data>>>"+json);
				//System.out.println("API Failed is>>>"+apinameUrl+"  at URL>>>"+fullUrl+">>>With data>>>"+json);
				String s="API Failed is>>>"+apinameUrl+">>>at URL>>>"+fullUrl+">>>With data>>>"+json;
				System.out.println("API NAME is :"+s.split(">>>")[1].split("_")[1]);
				System.out.println("API URL is :"+s.split(">>>")[3]);
				System.out.println("API Request is :"+s.split(">>>")[5]);
				
			}
			if (response.getStatus() == 200){
				isApiResponse=true;	
				message.getAPIErrorList().add("API Failed is>>>"+apinameUrl+">>>at URL>>>"+fullUrl+">>>With data>>>"+json);
				//System.out.println("API Failed is>>>"+apinameUrl+"  at URL>>>"+fullUrl+">>>With data>>>"+json);
				String s="API Failed is>>>"+apinameUrl+">>>at URL>>>"+fullUrl+">>>With data>>>"+json;
				System.out.println("API NAME is :"+s.split(">>>")[1].split("_")[0]);
				System.out.println("API URL is :"+s.split(">>>")[3]);
				System.out.println("API Request is :"+s.split(">>>")[5]);
				
				}
		 
	}catch(Exception e){
		e.printStackTrace();
	}
		return isApiResponse;
	}
	
	public Map<String,List<String>> listToMap(List<String>list){
		Map<String,List<String>> map=new HashMap<String, List<String>>();
		for(String s:list){
			String p=s.split(">>>")[1].split("_")[0];
			List l=map.get(p);
			if(l==null){
				List llist=new ArrayList<String>();
				llist.add(s);
				map.put(p, llist);
			}
			else{
				l.add(s);
			}
		}
		for (Entry<String, List<String>> entry : map.entrySet()) {
		    System.out.println("Key = " + entry.getKey() + ", ListSize = " + entry.getValue().size());
		    for(int i=0;i<entry.getValue().size();i++){
		    	System.out.println("Values :"+entry.getValue().get(i));
		    }
		}
		
		
		return map;
	}
	
	public static void main(String avi[]){
		BackendSystem obj = new BackendSystem();
		obj.readPro=new ReadProperty();
		System.out.println(obj.apiCheck("MOBAPI_getpdpContentBySupc_JSON","MOBAPI_getpdpContentBySupc_URL",new Message()));
		
	}
}
