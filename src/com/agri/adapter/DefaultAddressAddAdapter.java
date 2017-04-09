package com.agri.adapter;

import com.agri.bean.impl.AddressImpl;
import com.agri.bean.impl.CustomerImpl;
import com.myeclipseide.ws.DefaultAddressAdd;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by hyc on 2017/4/5.
 */
@XmlRootElement(name = "DefaultAddressAdd")
public class DefaultAddressAddAdapter extends DefaultAddressAdd {
    public DefaultAddressAddAdapter(){}
    public DefaultAddressAddAdapter(CustomerImpl customer, AddressImpl address){
        setAddressID(address.getId());
        setCustomerID(customer.getId());
    }
}
