package com.myeclipseide.ws;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class key {
	private int primaryKey;
	
	public int getPrimaryKey(){
		return primaryKey;
	}
	public void setPrimaryKey(int primaryKey){
		this.primaryKey = primaryKey;
	}
}
