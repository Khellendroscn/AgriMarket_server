package com.agri.adapter;

import com.agri.bean.impl.CustomerImpl;
import com.myeclipseide.ws.CustomerImage;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by hyc on 2017/4/5.
 */
@XmlRootElement(name = "CustomerImage")
public class CustomerImageAdapter extends CustomerImage {
    public CustomerImageAdapter(){}
    public CustomerImageAdapter(CustomerImpl customer){
        setCustomerID(customer.getId());
        setFeatureImage(customer.getInfo().getImgPath());
    }
}
