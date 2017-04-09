package com.myeclipseide.ws;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Image {
	private int customerID;
	private String imageName;
	private String imageString;
	
	public int getCustomerID(){
		return customerID;
	}
	public void setCustomerID(int customerID){
		this.customerID = customerID;
	}
	
	public String getImageName(){
		return imageName;
	}
	public void setImageName(String imageName){
		this.imageName = imageName;
	}
	
	public String getImageString(){
		return imageString;
	}
	public void setImageString(String imageString){
		this.imageString = imageString;
	}
}
