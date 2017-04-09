package com.agri.adapter;

import com.agri.bean.impl.CustomerServiceImpl;
import com.myeclipseide.ws.ExpertAdvisor;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by hyc on 2017/4/5.
 */
@XmlRootElement(name = "ExpertAdvisor")
public class ExpertAdvisorAdapter extends ExpertAdvisor {
    public ExpertAdvisorAdapter(){}
    public ExpertAdvisorAdapter(CustomerServiceImpl service){
        setCustomerID(service.getCustomerId());
        setEmail(service.getEmail());
        setQuestion(service.getQuestion());
        setTelNumber(service.getTel());
    }
}
