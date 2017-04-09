package com.myeclipseide.ws;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class FarmDetail {
	private String farmDescription;
	private String address;
	
	public String getFarmDescription(){
		return farmDescription;
	}
	public void setFarmDescription(String farmDescription){
		this.farmDescription = farmDescription;
	}
	
	public String getAddress(){
		return address;
	}
	public void setAddress(String address){
		this.address = address;
	}
}
