package com.snapdeal.datavalidator.util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
public class DataValidator {
	static PreparedStatement preparedStatement;
	
	 public boolean validateOffer(List<Integer> offers,Message message){
		boolean isSuccessfull=false;
		Set<Integer> offersSet = new HashSet<Integer>(offers);
		Iterator<Integer> itr= offersSet.iterator();
		while(itr.hasNext()){
			System.out.println("Call querying function from database to check if particular data is present or not");
			System.out.println("If the data is not present add error to errorList of MessageClass");
			System.out.println("Also find some 10 valid data and add this info in info list of error message");
			//Also make isSuccessfull to false 
		}
		return isSuccessfull;
	}
	
	

	public boolean validatevendorCode(List<String> vendorCode,Message message) throws Exception{
		boolean isSuccessfull=false;
		Set<String> vendorCodeSet = new HashSet<String>(vendorCode);
		Iterator<String> itr= vendorCodeSet.iterator();
		while(itr.hasNext()){
			String vendorCodee=itr.next();
			ResultSet r=getVendorcode(vendorCodee);
			r.beforeFirst();
			if(r.next()){
				int enable=r.getInt(6);
				if(enable!=1){
					message.getErrorMessageList().add("Error : The given vendor code is not enable on DB "+vendorCodee);
					isSuccessfull=true;
				}
			}
			else{
				message.getErrorMessageList().add("Error: The given vendor does not exist in the DB :"+vendorCodee);
			}
		}
		ResultSet infoResultSet=getVendorcodeInfo();
		infoResultSet.beforeFirst();
		message.getInfoMessageList().add("You can use the following valid VENDOR CODE :");
		String valideVendorCode="";
		while(infoResultSet.next()){
			String temp=infoResultSet.getString("code");
			valideVendorCode=valideVendorCode+temp+",";
		}
		message.getInfoMessageList().add(valideVendorCode);
		message.getInfoMessageList().add("Sample query for vendor code:");
		message.getInfoMessageList().add("select * from snapdeal_ipms.seller where enbled=1 limit 10;");
		
		return isSuccessfull;
	}
	
	public boolean validateBrandId(List<Integer>brandID,Message message) throws Exception{
		boolean isSuccessfull=false;
		Set<Integer> brandIDSet = new HashSet<Integer>(brandID);
		Iterator<Integer> itr= brandIDSet.iterator();
		while(itr.hasNext()){
			int brandIDs=itr.next();
			ResultSet r=getbrandID(brandIDs);
			r.beforeFirst();
			if(r.next()){
				int enable=r.getInt("enabled");
				if(enable!=1){
					message.getErrorMessageList().add("Error : The given brand ID is not enable on DB "+brandIDs);
				}
				isSuccessfull=true;
			}
			else{
				message.getErrorMessageList().add("Error: The given brand ids does not exist in the DB :"+brandIDs);
			} 
		 }
			ResultSet infoResultSet=getbrnadIDInfo();
			infoResultSet.beforeFirst();
			message.getInfoMessageList().add("You can use the following valid Brand ID :");
			String validBrandId="";
			while(infoResultSet.next()){
				String temp=infoResultSet.getString("brand_id");
				validBrandId=validBrandId+temp+",";
			}
			message.getInfoMessageList().add(validBrandId);
			message.getInfoMessageList().add("Sample query for valid Brand Id :");
			message.getInfoMessageList().add("select * from cams.brand_category_useful_links where enabled=1 limit 10;");
			
		
		return isSuccessfull;
	}
	
	public boolean validateShippingGroup(List<String>shoppingGroup,Message message){
		
		String validSG1="STD";
		String validSG2="COD-PRODUCTS";
		String validSG3="COD-VOUCHERS";
		String validSG4="EMAIL-MOBILE";
		boolean isSuccessfull=false;
		Set<String> shoppingGroupSet = new HashSet<String>(shoppingGroup);
		Iterator<String> itr= shoppingGroupSet.iterator();
		while(itr.hasNext()){
			String enteredShippingGroup=itr.next();
			if(enteredShippingGroup.equals(validSG1)||enteredShippingGroup.equals(validSG2)||enteredShippingGroup.equals(validSG3)||enteredShippingGroup.equals(validSG4))
			{
				isSuccessfull=true;
			}
			else{
				message.getErrorMessageList().add("Error the given shipping group is invalid");
			}
			message.getInfoMessageList().add("You can use the following shipping group  [STD,COD-PRODUCTS,COD-VOUCHERS,EMAIL-MOBILE]");
		}
		return isSuccessfull;
	}
	
	public boolean validateProductCategory(List<String>productCategory,Message message) throws Exception{
		boolean isSuccessfull=false;
		Set<String> productCategorySet = new HashSet<String>(productCategory);
		Iterator<String> itr= productCategorySet.iterator();
		while(itr.hasNext()){
			String url=itr.next();
			ResultSet r=getProductCategory(url);
			r.beforeFirst();
			if(r.next()){
					isSuccessfull=true;
				}
			else{
				message.getErrorMessageList().add("Error: The given product category URL does not exist in the DB :"+url);
			} 
		}
			ResultSet infoResultSet=getProductCategoryInfo();
			infoResultSet.beforeFirst();
			message.getInfoMessageList().add("You can use the following valid Product category :");
			String validUrl="";
			while(infoResultSet.next()){
				String temp=infoResultSet.getString("url");
				validUrl=validUrl+temp+",";
			}
			message.getInfoMessageList().add(validUrl);
			message.getInfoMessageList().add("Sample Query use for valid Brand Id :");
			message.getInfoMessageList().add("select * from cams.product_category limit 10;");
			return isSuccessfull;	
	}
		
	
	//It includes MRP,Sellingprice and MinimumSellingPrice 
	public boolean validatePricing(List<Integer>mrp,List<Integer>sellingPrice,List<Integer>minimumSellingPrice,Message message){
		boolean isSuccessfull=false;
		int mrpSize=mrp.size();
		int sellingPriceSize=mrp.size();
		int minimumSellingPriceSize=mrp.size();
		int count =0;
		//Check the size of all these three list must be equal else some value is missing in excel file
		if((mrpSize==sellingPriceSize) && (minimumSellingPriceSize==sellingPriceSize)){
			for(int i=0;i<mrpSize;i++){
				if((minimumSellingPrice.get(i)<=sellingPrice.get(i)) && 
					(sellingPrice.get(i)<=mrp.get(i))){
					count ++;
				}
				else {
					message.getErrorMessageList().add("Error :In pricing detail on the row "+i);
				}
			}
			if(count==mrpSize)
				isSuccessfull=true;
		}
		message.getInfoMessageList().add(" For Pricing Column please follow the rule :Minimum Selling price <=Selling price <=MRP");
		return isSuccessfull;
	}
	
	public boolean validateFreebieID(List<Integer>freebieId,Message message) throws Exception{
		boolean isSuccessfull=false;
		Set<Integer> freebieIdSet = new HashSet<Integer>(freebieId);
		Iterator<Integer> itr= freebieIdSet.iterator();
		while(itr.hasNext()){
		int freebieID=itr.next();
		ResultSet r=getFreebieID(freebieID);
		r.beforeFirst();
		if(r.next()){
			int shippable=r.getInt("shippable");
			if(shippable!=1)
				message.getErrorMessageList().add("Error : The given freebieID may cause problem during buy as it is not shipable"+freebieID);
				isSuccessfull=true;
			}
		else{
			message.getErrorMessageList().add("Error: The following freebie ID does not exist in the DB :"+freebieID);
		} 
		}
		ResultSet infoResultSet=getFreebieIDInfo();
		infoResultSet.beforeFirst();
		message.getInfoMessageList().add("You can use the following valid FreebieID :");
		String validFreebie="";
		while(infoResultSet.next()){
			int temp=infoResultSet.getInt("id");
			validFreebie=validFreebie+temp+",";
		}
		message.getInfoMessageList().add(validFreebie);
		message.getInfoMessageList().add("Sample query use valid Freebie Id :");
		message.getInfoMessageList().add("select * from cams.freebie where shippable=1 limit 10;");
		return isSuccessfull;
	}
	
	public boolean validateNavigationCategory(List<String>navigationCategoryList,Message message) throws Exception{
		boolean isSuccessfull=false;
		Set<String> navigationCategorySet = new HashSet<String>(navigationCategoryList);
		Iterator<String> itr= navigationCategorySet.iterator();
		while(itr.hasNext()){
			String displayName=itr.next();
			ResultSet r=getNavigationCategory(displayName);
			r.beforeFirst();
			if(r.next()){
				int enabled=r.getInt("enabled");
				if(enabled!=1)
					message.getErrorMessageList().add("Error : The given Navigation Category may cause problem as it is not enabled "+displayName);
					isSuccessfull=true;
				}
			else{
				message.getErrorMessageList().add("Error: The given Navigation Category does not exist in the DB :"+displayName);
			} 
		}
		ResultSet infoResultSet=getNavigationCategory();
		infoResultSet.beforeFirst();
		message.getInfoMessageList().add("You can use the following valid NavigationCategory :");
		String validNavigationCategory="";
		while(infoResultSet.next()){
			String temp=infoResultSet.getString("display_name");
			validNavigationCategory=validNavigationCategory+temp+",";
		}
		message.getInfoMessageList().add(validNavigationCategory);
		message.getInfoMessageList().add("Sample query use for valid Freebie Id :");
		message.getInfoMessageList().add("select * from cams.navigation_bucket where enabled =1 limit 10;");
		return isSuccessfull;
	}
	
	
	public boolean validateAttributes(List<String>attributeList,List<String>BrandID,Message message) throws Exception{
		boolean isSuccessfull=false;
		boolean isfound=false;
		Set<String> attributeSet = new HashSet<String>(attributeList);
		Set<String> BrandIDSet = new HashSet<String>(BrandID);
		Iterator<String> itr= BrandIDSet.iterator();
		while(itr.hasNext()){
			String displayName=itr.next();
			ResultSet r=getNavigationCategory(displayName);
			r.beforeFirst();
			for(String s : attributeSet){
			while(r.next()){
				String attribute=r.getString("attribute_name");
					if(s.equalsIgnoreCase(attribute))
						isfound=true;
				}
			if(!isfound){
				message.getErrorMessageList().add("Error the given attribute is not valid "+s);
			}
			isfound=false;
			} 
		}
		ResultSet infoResultSet=getNavigationCategory();
		infoResultSet.beforeFirst();
		message.getInfoMessageList().add("You can use the following valid NavigationCategory :");
		String validNavigationCategory="";
		while(infoResultSet.next()){
			String temp=infoResultSet.getString("display_name");
			validNavigationCategory=validNavigationCategory+temp+",";
		}
		message.getInfoMessageList().add(validNavigationCategory);
		message.getInfoMessageList().add("Sample query use for valid Freebie Id :");
		message.getInfoMessageList().add("select * from cams.navigation_bucket where enabled =1 limit 10;");
		return isSuccessfull;
	}
	
	
	
	/*public static ResultSet getOfferId(String offerId) throws Exception {
		 String query = "select * from snapdeal_ipms.seller where code=?;";
	        preparedStatement = DBUtil.connection.prepareStatement(query);
	    //    preparedStatement.setString(1, vendorCode);
	        ResultSet resultSet = preparedStatement.executeQuery();
	        return resultSet;
	    }*/
	
	public static ResultSet getAttributes(String brandId) throws Exception {
		 String query = "select * from cams.bucket_brand_attr_val_map  where brand_id=?;";
	        preparedStatement = DBUtil.connection.prepareStatement(query);
	        preparedStatement.setString(1, brandId);
	        ResultSet resultSet = preparedStatement.executeQuery();
	        return resultSet;
	    }
	
		
	
	
	
	public static ResultSet getVendorcode(String vendorCode) throws Exception {
        String query = "select * from snapdeal_ipms.seller where code=?;";
        preparedStatement = DBUtil.connection.prepareStatement(query);
        preparedStatement.setString(1, vendorCode);
        ResultSet resultSet = preparedStatement.executeQuery();
        return resultSet;
    }
	
	public static ResultSet getVendorcodeInfo() throws Exception {
        String query = "select * from snapdeal_ipms.seller where enabled=1 limit 10;";
        preparedStatement = DBUtil.connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        return resultSet;
    }
	
	public static ResultSet getbrandID(int brandID) throws Exception {
        String query = "select * from cams.brand_category_useful_links where brand_id=?;";
        preparedStatement = DBUtil.connection.prepareStatement(query);
        preparedStatement.setInt(1, brandID);
        ResultSet resultSet = preparedStatement.executeQuery();
        return resultSet;
    }
	
	public static ResultSet getbrnadIDInfo() throws Exception {
        String query = "select * from cams.brand_category_useful_links where enabled=1 limit 10;";
        preparedStatement = DBUtil.connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        return resultSet;
    }
	public static ResultSet getProductCategory(String url) throws Exception {
        String query = "select * from cams.product_category where url=?;";
        preparedStatement = DBUtil.connection.prepareStatement(query);
        preparedStatement.setString(1, url);
        ResultSet resultSet = preparedStatement.executeQuery();
        return resultSet;
    }
	public static ResultSet getProductCategoryInfo() throws Exception {
        String query = "select * from cams.product_category limit 10;";
        preparedStatement = DBUtil.connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        return resultSet;
    }
	public static ResultSet getFreebieID(int freebieID) throws Exception {
        String query = "select * from cams.freebie where id =? and shippable=1;";
        preparedStatement = DBUtil.connection.prepareStatement(query);
        preparedStatement.setInt(1, freebieID);
        ResultSet resultSet = preparedStatement.executeQuery();
        return resultSet;
    }
	public static ResultSet getFreebieIDInfo() throws Exception {
        String query = "select * from cams.freebie where shippable=1 limit 10;";
        preparedStatement = DBUtil.connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        return resultSet;
    }
	public static ResultSet getNavigationCategory(String displayName) throws Exception {
        String query = "select * from cams.navigation_bucket where display_name =? and enabled =1;";
        preparedStatement = DBUtil.connection.prepareStatement(query);
        preparedStatement.setString(1, displayName);
        ResultSet resultSet = preparedStatement.executeQuery();
        return resultSet;
    }
	public static ResultSet getNavigationCategory() throws Exception {
        String query = "select * from cams.navigation_bucket where enabled =1 limit 10;";
        preparedStatement = DBUtil.connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        return resultSet;
    }
	public static void getBrandID(String brandID){
		
	}
	
	
	
public static void main(String avi[]) throws Exception{
	//DBUsernameas7892IU=as7892IU,DBPassword=AnjS@522# ForwardPort=3011
	 //SSHCinnectionUtil.myStart("54.169.156.29", "as7892", "20.0.0.65", 3011, 3307);
	 //myStart(config.getConfig("SSHIP"), config.getConfig("SSHUsername"), config.getConfig("DBIP"), Integer.parseInt(config.getConfig("ForwardPort")),port);
	 System.out.println("Waiting");
	 //Thread.sleep(6000);
	 DBUtil.sqlConnection("3011","ak4776IU","a@6774#k");
	 System.out.println("Connected");
	 
	// ResultSet rs=getSuborder("52");
	 //rs.next();
	 //System.out.println("The value of column 3 is "+rs.getString(4));
	
}



}

