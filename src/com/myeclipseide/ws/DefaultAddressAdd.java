package com.myeclipseide.ws;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class DefaultAddressAdd {
	private int customerID;
	private int addressID;
	
	public int getCustomerID(){
		return customerID;
	}
	public void setCustomerID(int customerID){
		this.customerID = customerID;
	}
	
	public int getAddressID(){
		return addressID;
	}
	public void setAddressID(int addressID){
		this.addressID = addressID;
	}
}
