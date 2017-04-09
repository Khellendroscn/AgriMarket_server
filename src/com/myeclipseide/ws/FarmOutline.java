package com.myeclipseide.ws;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class FarmOutline {
	private int farmID;
	private String farmName;
	private String farmImage;
	private String telNumber;
	
	public int getFarmID(){
		return farmID;
	}
	public void setFarmID(int farmID){
		this.farmID = farmID;
	}
	
	public String getFarmName(){
		return farmName;
	}
	public void setFarmName(String farmName){
		this.farmName = farmName;
	}
	
	public String getFarmImage(){
		return farmImage;
	}
	public void setFarmImage(String farmImage){
		this.farmImage = farmImage;
	}
	
	public String getTelNumber(){
		return telNumber;
	}
	public void setTelNumber(String telNumber){
		this.telNumber = telNumber;
	}
}
