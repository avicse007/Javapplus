package com.snapdeal.datavalidator.util;

import java.util.ArrayList;
import java.util.List;

public class Message {
	private List<String> errorMessageList=new ArrayList<String>();
	private List<String> warningMessageList=new ArrayList<String>();
	private List<String> infoMessageList=new ArrayList<String>();
	public List<String> getErrorMessageList() {
		return errorMessageList;
	}
	public void setErrorMessageList(List<String> errorMessageList) {
		this.errorMessageList = errorMessageList;
	}
	public List<String> getWarningMessageList() {
		return warningMessageList;
	}
	public void setWarningMessageList(List<String> warningMessageList) {
		this.warningMessageList = warningMessageList;
	}
	public List<String> getInfoMessageList() {
		return infoMessageList;
	}
	public void setInfoMessageList(List<String> infoMessageList) {
		this.infoMessageList = infoMessageList;
	}
	
	

}
