package com.myeclipseide.ws;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ProductDescription {
	private String productName;
	private String productDescriptionString;
	
	public String getProductName(){
		return productName;
	}
	public void setProductName(String productName){
		this.productName = productName;
	}
	
	public String getProductDescriptionString(){
		return productDescriptionString;
	}
	public void setProductDescriptionString(String productDescriptionString){
		this.productDescriptionString = productDescriptionString;
	}
}
