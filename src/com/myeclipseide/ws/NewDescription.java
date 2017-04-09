package com.myeclipseide.ws;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class NewDescription {
	private String newImage;
	private String newDetail;
	
	
	public String getNewImage(){
		return newImage;
	}
	public void setNewImage(String newImage){
		this.newImage = newImage;
	}
	
	public String getNewDetail(){
		return newDetail;
	}
	public void setNewDetail(String newDetail){
		this.newDetail = newDetail;
	}
}
