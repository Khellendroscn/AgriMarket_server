package com.myeclipseide.ws;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class CustomerRequest {
	private int customerID;
	private String customerName;
	
	public int getCustomerID(){
		return customerID;
	}
	public void setCustomerID(int customerID){
		this.customerID = customerID;
	}
	
	public String getCustomerName(){
		return customerName;
	}
	public void setCustomerName(String customerName){
		this.customerName = customerName;
	}
	
}
