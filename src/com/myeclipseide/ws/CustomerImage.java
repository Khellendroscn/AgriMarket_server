package com.myeclipseide.ws;
import net.khe.db2.annotations.SqlInt;
import net.khe.db2.annotations.SqlString;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class CustomerImage {
	@SqlInt
	private int cutomerID;
	@SqlString()
	private String featureImage;
	
	public int getCustomerID(){
		return cutomerID;
	}
	public void setCustomerID(int cutomerID){
		this.cutomerID = cutomerID;
	}
	
	public String getFeatureImage(){
		return featureImage;
	}
	public void setFeatureImage(String featureImage){
		this.featureImage = featureImage;
	}
}
