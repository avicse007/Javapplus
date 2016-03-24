package com.snapdeal.ite4healthcheck.backendsystem;

import java.util.ArrayList;
import java.util.List;
/**
 * 
 * @author singh.avinash
 *
 */
public class Message {
	List<String> SystemErrorList=new ArrayList<String>();
	public List<String> getSystemErrorList() {
		return SystemErrorList;
	}
	public void setSystemErrorList(List<String> systemErrorList) {
		SystemErrorList = systemErrorList;
	}
	public List<String> getAPIErrorList() {
		return APIErrorList;
	}
	public void setAPIErrorList(List<String> aPIErrorList) {
		APIErrorList = aPIErrorList;
	}
	List<String> APIErrorList=new ArrayList<String>();

}
