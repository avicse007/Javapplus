package com.sanpdeal.backendsystems;

import com.snapdeal.datavalidator.config.ReadConfig;
import com.snapdeal.datavalidator.util.Message;

public class BackendSystemCheck {
	public void backendSystemCheck(Message message,ReadConfig rc){
		CAMS cams=new CAMS();
		COCOFS cocofs=new COCOFS();
		IPMS ipms=new IPMS();
		SCORE score =new SCORE();
		SHIPPING shipping=new SHIPPING();
		cams.getAllLabels(message,rc);
		cocofs.getFulfilmentModel(message,rc);
		ipms.getSellerInventoryPricingForPDP(message,rc);
		score.getSellerWiseDeliveryInfo(message,rc);
		System.out.println("Executing the shipping check");
		shipping.getShippingHistoryForOrderSummary(message,rc);
	}

}
