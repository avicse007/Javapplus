package com.snapdeal.ite4healthcheck.backendsystem;

import java.io.IOException;
import java.util.HashMap;

import com.snapdeal.ite4helthcheck.util.ReadProperty;
import com.snapdeal.ite4helthcheck.util.ReportGeneration;
import com.snapdeal.ite4helthcheck.util.SendEmails;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * 
 * @author singh.avinash
 *
 */
public class Main {

	public static void main(String[] args) {
		Message message = new Message();
		ReadProperty rp = new ReadProperty();
		BackendSystem bs = new BackendSystem();
		boolean isCatlogworking = false;
		boolean isCocofsworking = false;
		boolean isErasworking = false;
		boolean isIpmsworking = false;
		boolean isMobapiworking = false;
		boolean isOmsworking = false;
		boolean isOpsworking = false;
		boolean isPromoworking = false;
		boolean isReviewRatingworking = false;
		boolean isSearchworking = false;
		boolean isShippingWorking = false;
		boolean isReccomendationWorking = false;
		boolean isUmsWorking = false;
		boolean isCartWorking = false;
		boolean isSpmsPayment = false;
		boolean isScoreworking = false;
		boolean isScoreAdminWorking = false;
		boolean isFilms_Uiworking = false;
		bs.readPro = rp;
		isCatlogworking = bs.systemHelthcheck("CATALOG", message);
		if(isCatlogworking|| true){
			//bs.apiCheck(apinameJson, apinameUrl, message);
			bs.apiCheck("CAMS_getAllLabels_JSON", "CAMS_getAllLabels_URL", message);
			bs.apiCheck("CAMS_getPOGContentById_JSON", "CAMS_getPOGContentById_URL", message);
		}
		isCocofsworking = bs.systemHelthcheck("COCOFS", message);
		if(isCocofsworking|| true){
			bs.apiCheck("COCOFS_getFulfilmentModel_JSON","COCOFS_getFulfilmentModel_URL", message);
			bs.apiCheck("COCOFS_getFulfilmentModelBySellerSupcSubcat_JSON", "COCFS_getFulfilmentModelBySellerSupcSubcat_URL", message);
		}
		isErasworking = bs.systemHelthcheck("ERAS", message);
		if(isErasworking|| true){
			//bs.apiCheck(apinameJson, apinameUrl, message);
		}
		isIpmsworking = bs.systemHelthcheck("IPMS", message);
		if(isIpmsworking|| true){
			bs.apiCheck("IPMS_getSellerInventoryPricingForPDP_JSON", "IPMS_getSellerInventoryPricingForPDP_URL", message);
		}
		isMobapiworking = bs.systemHelthcheck("MobAPI", message);
		if(isMobapiworking|| true){
			bs.apiCheck("MOBAPI_getpdpContentBySupc_JSON", "MOBAPI_getpdpContentBySupc_URL", message);
			bs.apiCheck("MOBAPI_gvps_JSON", "MOBAPI_gvbps_URL", message);
			bs.apiCheck("MOBAPI_getO2ODeliveryServiceability_JSON", "MOBAPI_getO2ODeliveryServiceability_URL", message);
			
		}
		isOmsworking = bs.systemHelthcheck("OMS", message);
		if(isOmsworking|| true){
			bs.apiCheck("OMS_getSuborderHistoryStatusesByCode_JSON", "OMS_getSuborderHistoryStatusesByCode_URL", message);
			bs.apiCheck("OMS_getOrderCount_JSON", "OMS_getOrderCount_URL", message);
			
		}
		isOpsworking = bs.systemHelthcheck("OPS", message);
		if(isOpsworking|| true){
			//bs.apiCheck(apinameJson, apinameUrl, message);
		}
		isPromoworking = bs.systemHelthcheck("PROMO", message);
		if(isPromoworking|| true){
			bs.apiCheck("PROMO_getOfferStrips_JSON", "PROMO_getOfferStrips_URL", message);
			bs.apiCheck("PROMO_getPromoWindowPDPByPogId_JSON", "PROMO_getPromoWindowPDPByPogId_URL", message);
		}
		isReviewRatingworking = bs.systemHelthcheck("REVIEW_AND_RATING", message);
		if(isReviewRatingworking|| true){
			//bs.apiCheck(apinameJson, apinameUrl, message);
		}
		isSearchworking = bs.systemHelthcheck("SEARCH", message);
		if(isSearchworking|| true){
			bs.apiCheck("SEARCH_search_JSON", "SEARCH_search_URL", message);
			bs.apiCheck("SEARCH_searchGroupLeftNav_JSON", "SEARCH_searchGroupLeftNav_URL", message);
		}
		isShippingWorking = bs.systemHelthcheck("SHIPING_SELLER", message);
		if(isShippingWorking|| true){
			bs.apiCheck("SHIPPING_getShippingHistoryForOrderSummary_JSON", "SHIPPING_getShippingHistoryForOrderSummary_URL", message);
			bs.apiCheck("SHIPPING_checkIfCancellable_JSON", "SHIPPING_checkIfCancellable_URL", message);
		}
		isReccomendationWorking = bs.systemHelthcheck("RECOMMENDATION", message);
		if(isReccomendationWorking|| true){
			bs.apiCheck("RECOMENDATION_getSimilarPOGIdListById_JSON", "RECOMENDATION_getSimilarPOGIdListById_URL", message);
			bs.apiCheck("RECOMENDATION_getRecommendedForYouPOGIdListByEmail_JSON", "RECOMENDATION_getRecommendedForYouPOGIdListByEmail_URL", message);
			bs.apiCheck("RECOMENDATION_getFrequentlyBoughtTogetherPOGIdListById_JSON", "RECOMENDATION_getFrequentlyBoughtTogetherPOGIdListById_URL", message);
		}
		isUmsWorking = bs.systemHelthcheck("UMS", message);
		if(isUmsWorking|| true){
			bs.apiCheck("UMS_getEmailVerificationCode_JSON", "UMS_getEmailVerificationCode_URL", message);
			
		}
		isCartWorking = bs.systemHelthcheck("CART", message);
		if(isCartWorking|| true){
			//bs.apiCheck(apinameJson, apinameUrl, message);
		}
		isSpmsPayment = bs.systemHelthcheck("SPMS_PAYMENT", message);
		if(isSpmsPayment|| true){
			//bs.apiCheck(apinameJson, apinameUrl, message);
		}
		isScoreworking = bs.systemHelthcheck("SCORE", message);
		if(isScoreworking|| true){
			bs.apiCheck("SCORE_getSellerWiseDeliveryInfo_JSON", "SCORE_getSellerWiseDeliveryInfo_URL", message);
		}
		isScoreAdminWorking = bs.systemHelthcheck("SCORE_ADMIN", message);
		if(isScoreAdminWorking|| true){
			//bs.apiCheck(apinameJson, apinameUrl, message);
		}
		isFilms_Uiworking = bs.systemHelthcheck("FILMS_UI", message);
		if(isScoreAdminWorking|| true){
			//bs.apiCheck(apinameJson, apinameUrl, message);
		}
		int systemErrorlistSize = message.getSystemErrorList().size();
		//bs.listToMap(message.APIErrorList);
		
		
		if (systemErrorlistSize > 0) {
			// sendMail;
			try {
				ReportGeneration.generateReport(message.getSystemErrorList(), bs.listToMap(message.APIErrorList));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String mail=args[0];
			if (mail.isEmpty() || mail == null){
				System.out.println("The email id received is null ");
				mail="singh.avinash@snadeal.com";
			}
			else
				System.out.println("The email id received is " + mail);
			System.out.println("Snending mail to "+mail);
			SendEmails.sendEmail(mail);
			System.out.println("Size of error list is " + message.getSystemErrorList().size());
			for (int i = 0; i < message.getSystemErrorList().size(); i++) {
				//System.out.println(message.getSystemErrorList().get(i));
				System.err.println(message.getSystemErrorList().get(i));
			}
			boolean isSizeZero=true;
			if(systemErrorlistSize>0)
				isSizeZero=false;
			Assert.assertTrue(isSizeZero, "Few System are failing to response");
		
		}
		
	}

}
