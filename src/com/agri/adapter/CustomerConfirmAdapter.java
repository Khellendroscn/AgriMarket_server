package com.agri.adapter;

import com.agri.bean.impl.CustomerImpl;
import com.myeclipseide.ws.CustomerConfirm;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by hyc on 2017/4/5.
 */
@XmlRootElement(name = "CustomerConfirm")
public class CustomerConfirmAdapter extends CustomerConfirm {
    public CustomerConfirmAdapter(){}
    public CustomerConfirmAdapter(CustomerImpl customer){
        setCustomerName(customer.getUserName());
        setPassword(customer.getPassword());
    }
}
