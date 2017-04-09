package com.myeclipseide.ws;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class NewOutline {
	private int newID;
	private String newName;
	private String newImage;
	private String newDate;
	
	public int getNewID(){
		return newID;
	}
	public void setNewID(int newID){
		this.newID = newID;
	}
	
	public String getNewName(){
		return newName;
	}
	public void setNewName(String newName){
		this.newName = newName;
	}
	
	public String getNewImage(){
		return newImage;
	}
	public void setNewImage(String newImage){
		this.newImage = newImage;
	}
	
	public String getNewDate(){
		return newDate;
	}
	public void setNewDate(String newDate){
		this.newDate = newDate;
	}
}
