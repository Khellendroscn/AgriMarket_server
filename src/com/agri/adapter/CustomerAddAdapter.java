package com.agri.adapter;

import com.agri.bean.impl.CustomerImpl;
import com.myeclipseide.ws.CustomerAdd;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by hyc on 2017/4/5.
 */
@XmlRootElement(name = "CustomerAdd")
public class CustomerAddAdapter extends CustomerAdd {
    public CustomerAddAdapter(){}
    public CustomerAddAdapter(CustomerImpl customer){
        setCustomerName(customer.getUserName());
        setPassword(customer.getPassword());
        setEmail(customer.getInfo().getEmail());
    }
}
