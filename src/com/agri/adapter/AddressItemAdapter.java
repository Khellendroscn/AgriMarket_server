package com.agri.adapter;

import com.agri.bean.impl.AddressImpl;
import com.myeclipseide.ws.AddressItem;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by hyc on 2017/4/5.
 */
@XmlRootElement(name = "AddressItem")
public class AddressItemAdapter extends AddressItem {
    public AddressItemAdapter(){}
    public AddressItemAdapter(AddressImpl addr){
        setAddress(addr.getContent());
        setAddressID(addr.getId());
        setTelNumber(addr.getTel());
        setCustomerID(addr.getCustomerId());
        setName(addr.getName());
    }
}
