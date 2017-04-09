package com.myeclipseide.ws;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class OrderDetailItem {
	private int productID;
	private String productName;
	private int productCount;
	private float sum;
	private String productImage;
	
	public int getProductID(){
		return productID;
	}
	public void setProductID(int productID){
		this.productID = productID;
	}
	
	
	public String getProductName(){
		return productName;
	}
	public void setProductName(String productName){
		this.productName = productName;
	}

	
	public int getProductCount(){
		return productCount;
	}
	public void setProductCount(int productCount){
		this.productCount = productCount;
	}
	
	public float getSum(){
		return sum;
	}
	public void setSum(float sum){
		this.sum = sum;
	}
	
	public String getProductImage(){
		return productImage;
	}
	public void setProductImage(String productImage){
		this.productImage = productImage;
	}
}
