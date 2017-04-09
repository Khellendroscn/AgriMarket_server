package com.agri.adapter;

import com.agri.bean.impl.CustomerImpl;
import com.myeclipseide.ws.CustomerRequest;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by hyc on 2017/4/5.
 */
@XmlRootElement(name = "CustomerRequest")
public class CustomerRequestAdapter extends CustomerRequest {
    public CustomerRequestAdapter(){}
    public CustomerRequestAdapter(CustomerImpl customer){
        setCustomerID(customer.getId());
        setCustomerName(customer.getUserName());
    }
}
