package com.myeclipseide.ws;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class OrderItemRequest {
	private int productID;
	private int productCount;
	
	public int getProductID(){
		return productID;
	}
	public void setProductID(int productID){
		this.productID = productID;
	}
	
	public int getProductCount(){
		return productCount;
	}
	public void setProductCount(int productCount){
		this.productCount = productCount;
	}
}
