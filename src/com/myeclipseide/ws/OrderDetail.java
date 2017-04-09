package com.myeclipseide.ws;
import java.sql.Timestamp;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class OrderDetail <Timestamp>{
	private String address;
	private String telNumber;
	private String name;
	private String farmName;
	private int farmID;
	private String status;
	private int orderID;
	private Timestamp dateCreate;
	
	public Timestamp getDateCreate(){
		return dateCreate;
	}
	public void setDateCreate(Timestamp dateCreate){
		this.dateCreate = dateCreate;
	}
	
	public int getOrderID(){
		return orderID;
	}
	public void setOrderID(int orderID){
		this.orderID = orderID;
	}
	
	public String getAddress(){
		return address;
	}
	public void setAddress(String address){
		this.address = address;
	}
	
	public String getTelNumber(){
		return telNumber;
	}
	public void setTelNumber(String telNumber){
		this.telNumber = telNumber;
	}
	
	public String getName(){
		return name;
	}
	public void setName(String name){
		this.name = name;
	}
	
	public String getFarmName(){
		return farmName;
	}
	public void setFarmName(String farmName){
		this.farmName = farmName;
	}
	
	public int getFarmID(){
		return farmID;
	}
	public void setFarmID(int farmID){
		this.farmID = farmID;
	}
	
	public String getStatus(){
		return status;
	}
	public void setStatus(String status){
		this.status = status;
	}
}
