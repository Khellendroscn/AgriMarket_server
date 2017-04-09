package com.myeclipseide.ws;
import net.khe.db2.annotations.DBTable;
import net.khe.db2.annotations.SqlString;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@DBTable
public class CustomerConfirm {
	@SqlString(20)
	private String customerName;
	@SqlString(32)
	private String password;
	
	public String getCustomerName(){
		return customerName;
	}
	public void setCustomerName(String customerName){
		this.customerName = customerName;
	}
	
	public String getPassword(){
		return password;
	}
	public void setPassword(String password){
		this.password = password;
	}
	
}
