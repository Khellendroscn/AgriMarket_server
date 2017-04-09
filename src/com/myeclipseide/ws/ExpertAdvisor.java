package com.myeclipseide.ws;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ExpertAdvisor {
	private String question;
	private String telNumber;
	private String email;
	private int customerID;

	
	public int getCustomerID(){
		return customerID;
	}
	public void setCustomerID(int customerID){
		this.customerID = customerID;
	}
	
	
	public String getQuestion(){
		return question;
	}
	public void setQuestion(String question){
		this.question = question;
	}
	
	public String getTelNumber(){
		return telNumber;
	}
	public void setTelNumber(String telNumber){
		this.telNumber = telNumber;
	}
	
	public String getEmail(){
		return email;
	}
	public void setEmail(String email){
		this.email = email;
	}
}
