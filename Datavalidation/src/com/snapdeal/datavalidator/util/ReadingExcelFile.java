package com.snapdeal.datavalidator.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Row.MissingCellPolicy;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadingExcelFile {
	
	private List<Integer> offer=new ArrayList<Integer>();
	private List<String> vendorCode=new ArrayList<String>();
	private List<Integer> brandID=new ArrayList<Integer>();
	private List<String> shoppingGroup=new ArrayList<String>();
	private List<String> productCategory=new ArrayList<String>();
	private List<Integer> mrp = new ArrayList<Integer>();
	private List<Integer> sellingPrice=new ArrayList<Integer>();
	private List<Integer> freebieID=new ArrayList<Integer>();
	private List<Integer> minimumSellingPrice=new ArrayList<Integer>();
	private List<String> navigationCategory=new ArrayList<String>();
	private List<String> shippingGroup = new ArrayList<String>();
	private Set<String> sku=new HashSet<String>();
	private List<String> attributeName1=new ArrayList<String>();
	private List<String> attributeName2=new ArrayList<String>();
	private List<String> attributeName3=new ArrayList<String>();
	private List<String> attributeValue1=new ArrayList<String>();
	private List<String> attributeValue2=new ArrayList<String>();
	private List<String> attributeValue3=new ArrayList<String>();
	private List<String> filterName1=new ArrayList<String>();
	private List<String> filterName2=new ArrayList<String>();
	private List<String> filterName3=new ArrayList<String>();
	private List<String> filterValue1=new ArrayList<String>();
	private List<String> filterValue2=new ArrayList<String>();
	private List<String> filterValue3=new ArrayList<String>();
	private int offerCol;
	private int skuCol;
	private int vendorCodeCol;
	private int brandIDCol;
	private int attribute1NameCol;
	private int attribute1ValueCol;
	private int attribute2NameCol;
	private int attribute2ValueCol;
	private int attribute3NameCol;
	private int attribute3ValueCol;
	private int shipingGroupCol;
	private int productCategoryCol;
	private int filter1NameCol;
	private int filter1ValueCol;
	private int filter2NameCol;
	private int filter2ValueCol;
	private int filter3NameCol;
	private int filter3ValueCol;
	private int mrpCol;
	private int sellinPriceCol;
	private int minimumSellingPriceCol;
	private int freebieIDCol;
	private int navigationCaegoryCol;
	
	
	
	public List<String> getAttributeName1() {
		return attributeName1;
	}
	public void setAttributeName1(List<String> attributeName1) {
		this.attributeName1 = attributeName1;
	}
	public List<String> getAttributeName2() {
		return attributeName2;
	}
	public void setAttributeName2(List<String> attributeName2) {
		this.attributeName2 = attributeName2;
	}
	public List<String> getAttributeValue1() {
		return attributeValue1;
	}
	public void setAttributeValue1(List<String> attributeValue1) {
		this.attributeValue1 = attributeValue1;
	}
	public List<String> getAttributeValue2() {
		return attributeValue2;
	}
	public void setAttributeValue2(List<String> attributeValue2) {
		this.attributeValue2 = attributeValue2;
	}
	public List<String> getFilterName1() {
		return filterName1;
	}
	public void setFilterName1(List<String> filterName1) {
		this.filterName1 = filterName1;
	}
	public List<String> getFilterName2() {
		return filterName2;
	}
	public void setFilterName2(List<String> filterName2) {
		this.filterName2 = filterName2;
	}
	public List<String> getFilterValue1() {
		return filterValue1;
	}
	public void setFilterValue1(List<String> filterValue1) {
		this.filterValue1 = filterValue1;
	}
	public List<String> getFilterValue2() {
		return filterValue2;
	}
	public void setFilterValue2(List<String> filterValue2) {
		this.filterValue2 = filterValue2;
	}
	
	public List<Integer> getOffer() {
		return offer;
	}
	public void setOffer(List<Integer> offer) {
		this.offer = offer;
	}
	public Set<String> getSku() {
		return sku;
	}
	public void setSku(Set<String> sku) {
		this.sku = sku;
	}
	public List<String> getVendorCode() {
		return vendorCode;
	}
	public void setVendorCode(List<String> vendorCode) {
		this.vendorCode = vendorCode;
	}
	public List<Integer> getBrandID() {
		return brandID;
	}
	public void setBrandID(List<Integer> brandID) {
		this.brandID = brandID;
	}
	public List<String> getShoppingGroup() {
		return shoppingGroup;
	}
	public void setShoppingGroup(List<String> shoppingGroup) {
		this.shoppingGroup = shoppingGroup;
	}
	public List<String> getProductCategory() {
		return productCategory;
	}
	public void setProductCategory(List<String> productCategory) {
		this.productCategory = productCategory;
	}
	public List<Integer> getMrp() {
		return mrp;
	}
	public void setMrp(List<Integer> mrp) {
		this.mrp = mrp;
	}
	public List<Integer> getSellingPrice() {
		return sellingPrice;
	}
	public void setSellingPrice(List<Integer> sellingPrice) {
		this.sellingPrice = sellingPrice;
	}
	public List<Integer> getFreebieID() {
		return freebieID;
	}
	public void setFreebieID(List<Integer> freebieID) {
		this.freebieID = freebieID;
	}
	public List<Integer> getMinimumSellingPrice() {
		return minimumSellingPrice;
	}
	public void setMinimumSellingPrice(List<Integer> minimumSellingPrice) {
		this.minimumSellingPrice = minimumSellingPrice;
	}
	public List<String> getNavigationCategory() {
		return navigationCategory;
	}
	public void setNavigationCategory(List<String> navigationCategory) {
		this.navigationCategory = navigationCategory;
	}
	public List<String> getShippingGroup() {
		return shippingGroup;
	}
	public void setShippingGroup(List<String> shippingGroup) {
		this.shippingGroup = shippingGroup;
	}
	
	public  void readExcelFile(String path,Message message,int filterlevel,int attributelevel) throws IOException{
		
		 FileInputStream file = null;
	        try
	        {
	        	//String filePath = System.getProperty("user.dir")+"//"+filePath1;
	             file = new FileInputStream(new File(path));
	 
	            //Create Workbook instance holding reference to .xlsx file
	            XSSFWorkbook workbook = new XSSFWorkbook(file);
	 
	            //Get first/desired sheet from the workbook
	            XSSFSheet sheet = workbook.getSheetAt(0); 
	            //Deleting the empty row
	            try{
	            	int k=sheet.getLastRowNum();
	            for(int i=0;i<=k;i++){
	            	if(isRowEmpty(sheet.getRow(i))){
	            		if(i<=10)
	            		System.out.println("Row number is empty"+i);
	            		sheet.removeRow(sheet.getRow(i));
	            	}
	            	
	            }
	            }catch(Exception e){
	            	System.out.println("Getting exception here :"+e);
	            }
	            System.out.println("We are here and printing this out");
	            int rows=0;
	            int totalCols=0;
	            int cols=0;
	            Iterator<Row> rowIterator1 = sheet.iterator();
	            while(rows<1){
	            	 System.out.println("We are inside loop now with rows value as "+rows);
	            	Row row = rowIterator1.next();
	            	Iterator<Cell> cellIterator = row.cellIterator();
	            	while (cellIterator.hasNext()){
	            		Cell cell= cellIterator.next();
	            		if(cell==null||cell.getCellType()==cell.CELL_TYPE_BLANK){
	            			rows=100;
	            			break;
	            		}
	            		else{ 
	            			String cellvalue=cell.getStringCellValue();
	            			if(cellvalue.equalsIgnoreCase("Offer")){
	            				offerCol=cols;
	            				System.out.println("Cols for offer is :"+offerCol);
	            			}
	            			else if(cellvalue.equalsIgnoreCase("SKU")){
	            				skuCol=cols;
	            				System.out.println("Cols for skuCol is :"+skuCol);
	            			}
	            			else if(cellvalue.equalsIgnoreCase("Vendor Code")){
	            				vendorCodeCol=cols;
	            				System.out.println("Cols for vendorCodeCol is :"+vendorCodeCol);
	            			}
	            			else if(cellvalue.equalsIgnoreCase("BrandId")){
	            				brandIDCol=cols;
	            				System.out.println("Cols for brandIDCol is :"+brandIDCol);
	            				
	            			}
	            			else if(cellvalue.equalsIgnoreCase("Attribute1-Name")){
	            				attribute1NameCol=cols;
	            				System.out.println("Cols for attribute1NameCol is :"+attribute1NameCol);
	            			}
	            			else if(cellvalue.equalsIgnoreCase("Attribute1-Value")){
	            				attribute1ValueCol=cols;
	            				System.out.println("Cols for attribute1ValueCol is :"+attribute1ValueCol);
	            				
	            			}
	            			else if(cellvalue.equalsIgnoreCase("Attribute2-Name")){
	            				attribute2NameCol=cols;
	            				System.out.println("Cols for attribute2NameCol is :"+attribute2NameCol);	
	            			}
	            			else if(cellvalue.equalsIgnoreCase("Attribute2-Value")){
	            				attribute2ValueCol=cols;
	            				System.out.println("Cols for attribute2ValueCol is :"+attribute2ValueCol);	
	            			}
	            			else if(cellvalue.equalsIgnoreCase("Attribute3-Name")){
	            				attribute3NameCol=cols;
	            				System.out.println("Cols for attribute3NameCol is :"+attribute2ValueCol);	
	            			}
	            			else if(cellvalue.equalsIgnoreCase("Attribute3-Value")){
	            				attribute3ValueCol=cols;
	            				System.out.println("Cols for attribute3ValueCol is :"+attribute3ValueCol);
	            			}
	            			else if(cellvalue.equalsIgnoreCase("Shipping Group")){
	            				shipingGroupCol=cols;
	            				System.out.println("Cols for shipingGroupCol is :"+shipingGroupCol);
	            			}
                            else if(cellvalue.equalsIgnoreCase("Product Category")){
                            	productCategoryCol=cols;
	            				System.out.println("Cols for productCategoryCol is :"+productCategoryCol);
	            			}
                            else if(cellvalue.equalsIgnoreCase("Filter1-Name")){
                            	filter1NameCol=cols;
	            				System.out.println("Cols for filter1NameCol is :"+filter1NameCol);
	            			}
                            else if(cellvalue.equalsIgnoreCase("Filter1-Value")){
                            	filter1ValueCol=cols;
	            				System.out.println("Cols for filter1ValueCol is :"+filter1ValueCol);
	            			}
                            else if(cellvalue.equalsIgnoreCase("Filter2-Name")){
                            	filter2NameCol=cols;
	            				System.out.println("Cols for filter2NameCol is :"+filter2NameCol);
	            				
	            			}
                            else if(cellvalue.equalsIgnoreCase("Filter2-Value")){
                            	filter2ValueCol=cols;
	            				System.out.println("Cols for filter2ValueCol is :"+filter2ValueCol);
	            			}
                            else if(cellvalue.equalsIgnoreCase("Filter3-Name")){
                            	filter3NameCol=cols;
	            				System.out.println("Cols for filter3NameCol is :"+filter3NameCol);
	            			}
                            else if(cellvalue.equalsIgnoreCase("Filter3-Value")){
                            	filter3ValueCol=cols;
	            				System.out.println("Cols for filter3ValueCol is :"+filter3ValueCol);
	            			}
                            else if(cellvalue.equalsIgnoreCase("MRP")){
                            	mrpCol=cols;
	            				System.out.println("Cols for mrpCol is :"+mrpCol);
	            			}
	            			 else if(cellvalue.equalsIgnoreCase("Selling Price")){
	            				    sellinPriceCol=cols;
		            				System.out.println("Cols for sellinPriceCol is :"+sellinPriceCol);
		            		}
	            			 else if(cellvalue.equalsIgnoreCase("Freebie Id")){
	            				 freebieIDCol=cols;
		            				System.out.println("Cols for freebieIDCol is :"+freebieIDCol);
			            	}
	            			 else if(cellvalue.equalsIgnoreCase("Navigation Category")){
	            				 navigationCaegoryCol=cols;
		            				System.out.println("Cols for navigationCaegoryCol is :"+navigationCaegoryCol);
				            }
	            			
	            		}
	            		System.out.println(" Cell value :"+cell.getStringCellValue());
	            		totalCols++;
	            		cols++;
	            	}
	            	rows=10;
	            }
	            System.out.println("Total number of cols to be considered "+totalCols);
	            
	            
	            //Iterate through each rows one by one
	            Iterator<Row> rowIterator = sheet.iterator();
	            //marking which row to read 
	            int rowCount =0;
	            
	            while (rowIterator.hasNext())
	            {
	                Row row =  rowIterator.next();
	                //For each row, iterate through all the columns
	                Iterator<Cell> cellIterator = row.cellIterator();
	                //marking which column to read
	                int colcount=0;
	                int counter=0;
	                while (cellIterator.hasNext() && colcount<=totalCols)
	                {
	                	counter++;
	                    Cell cell = cellIterator.next();
	                    if(rowCount>=1){
	                    	//If the cell is empty or null add it to warning
	                          if(cell==null || cell.getCellType()==cell.CELL_TYPE_BLANK){
	                        	  System.out.println("The cell is empty with row value :"+rowCount+" and column value "+colcount);
	                        	  message.getWarningMessageList().add("The cell is empty with row value :"+rowCount+" and column value "+colcount);
	                          }
	                          readCellValueAndaddToList(colcount, offerCol, rowCount, cell, offer, "Offer", message);
	                          if(colcount==skuCol){
	                        	  System.out.println("SKU values "+cell.getStringCellValue());
		                        	 boolean temp= sku.add(cell.getStringCellValue());
		                        	 if(!temp){
		                        		 System.out.println("Error: Duplicate sku value at  row"+rowCount+", col "+colcount);
		                        		 message.getErrorMessageList().add("Error: Duplicate sku value at  row"+rowCount+", col "+colcount);
		                        	 }
	                          }
	                          readCellValueAndaddToList(colcount,vendorCodeCol, rowCount, cell, vendorCode, "VendorCode", message);
	                          readCellValueAndaddToList(colcount, brandIDCol, rowCount, cell, brandID, "BrandID", message);
	                          if(attributelevel>=1){
	                          readCellValueAndaddToList(colcount, attribute1NameCol, rowCount, cell, attributeName1, "attribute1name", message);
	                          readCellValueAndaddToList(colcount, attribute1ValueCol, rowCount, cell, attributeValue1, "Attribute1Value", message);
	                          }
	                          if(attributelevel>=2){
	                          readCellValueAndaddToList(colcount, attribute2NameCol, rowCount, cell, attributeName2, "attributeName2", message);
	                          readCellValueAndaddToList(colcount, attribute2ValueCol, rowCount, cell, attributeValue2, "attributeValue2", message);
	                          }
	                          if(attributelevel==3){
	                          readCellValueAndaddToList(colcount, attribute3NameCol, rowCount, cell,attributeName3, "attributeName3", message);
	                          readCellValueAndaddToList(colcount, attribute3ValueCol, rowCount, cell,attributeValue3, "attributeName3", message);
	                          }
	                          readCellValueAndaddToList(colcount, shipingGroupCol, rowCount, cell, shippingGroup, "shippingGroup", message);
	                          readCellValueAndaddToList(colcount, productCategoryCol, rowCount, cell, productCategory, "productCategory", message);
	                          if(filterlevel>=1){
	                          readCellValueAndaddToList(colcount, filter1NameCol, rowCount, cell, filterName1, "filterName1", message);
	                          readCellValueAndaddToList(colcount, filter1ValueCol, rowCount, cell, filterValue1, "filterValue1", message);
	                          }
	                          if(filterlevel>=2){
	                          readCellValueAndaddToList(colcount, filter2NameCol, rowCount, cell, filterName2, "filterName2", message);
	                          readCellValueAndaddToList(colcount, filter2ValueCol, rowCount, cell, filterValue2, "filterValue2", message);
	                          }
	                          if(filterlevel==3){
	                          readCellValueAndaddToList(colcount, filter3NameCol, rowCount, cell, filterName3, "filterName3", message);
	                          readCellValueAndaddToList(colcount, filter3ValueCol, rowCount, cell, filterValue3, "filterValue3", message);
	                          }
	                          readCellValueAndaddToList(colcount, mrpCol, rowCount, cell, mrp, "MRP", message);
	                          readCellValueAndaddToList(colcount, sellinPriceCol, rowCount, cell, sellingPrice, "sellingPrice", message);
	                          readCellValueAndaddToList(colcount, minimumSellingPriceCol, rowCount, cell, minimumSellingPrice, "minimumSellingPrice", message);
	                          readCellValueAndaddToList(colcount, freebieIDCol, rowCount, cell, freebieID, "freebieID", message);
	                          readCellValueAndaddToList(colcount, navigationCaegoryCol, rowCount, cell, navigationCategory, "navigationCategory", message);  
	                         // System.out.println("Value of counter "+counter);
	                    colcount++;
	                
	                    }//closing if row is not zero
	                }
	                //System.out.println("");
	                rowCount++;
	            }
	            
	        }
	        catch (Exception e)
	        {
	            e.printStackTrace();
	        }
	        finally{
	        	file.close();
	        }
	}
	
	public static boolean isRowEmpty(Row row) {
		int k=row.getLastCellNum();
	    for (int c = row.getFirstCellNum(); c < k; c++) {
	        Cell cell = row.getCell(c);
	        if (!(cell == null) && !((int)cell.getCellType() == Cell.CELL_TYPE_BLANK))
	        {
	        	if(cell.getCellType()==Cell.CELL_TYPE_NUMERIC){
	        		System.out.println("Getting cell value" +cell.getNumericCellValue());
	        	}
	        	if(cell.getCellType()==Cell.CELL_TYPE_STRING){
	        		System.out.println("Getting cell value" +cell.getStringCellValue());
	        	}
	        	
	        	return false;
	        }
	            
	    }
	    return true;
	}
	
	
	@SuppressWarnings("unchecked")
	public void readCellValueAndaddToList(int currentcol,int col,int rowCount,Cell cell,List list,String name,Message message){
    	if(currentcol==col){
        	if(cell.getCellType()==Cell.CELL_TYPE_NUMERIC){
        		list.add((int)cell.getNumericCellValue());
        		System.out.println(name+" Value is "+(int)cell.getNumericCellValue());
        	}
        	else if(cell.getCellType()==Cell.CELL_TYPE_STRING){
        		list.add((cell.getStringCellValue().trim()));
        		System.out.println(name+" Value is "+cell.getStringCellValue());
        	}
        	else{
        	  message.getErrorMessageList().add("Error: The"+name+" value is blank at the position(row,col): ("+rowCount+","+currentcol+")");
        	System.out.println("Error: The"+name + "value is blank at the position(row,col): ("+rowCount+","+currentcol+")");
        	}
        	}
    	
    }
	//Public static void main
	public static void main(String args[]) throws IOException{
		ReadingExcelFile fr= new ReadingExcelFile();
		 Message message=new Message();
		fr.readExcelFile("C:\\Users\\singh.avinash\\Downloads\\Data.xlsx",message,3,3);
		for(String s : message.getErrorMessageList())
			System.out.println(s);
		
	}
	
	
	
	
}
