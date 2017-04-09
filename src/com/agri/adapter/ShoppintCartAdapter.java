package com.agri.adapter;

import com.agri.bean.impl.ShoppingCartItemImpl;
import com.myeclipseide.ws.ShoppingCart;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by hyc on 2017/4/6.
 */
@XmlRootElement(name = "ShoppingCart")
public class ShoppintCartAdapter extends ShoppingCart {
    public ShoppintCartAdapter(){}
    public ShoppintCartAdapter(ShoppingCartItemImpl item){
        setCustomerID(item.getCustomerId());
        setProductCount(item.getAmount());
        setProductID(item.getProductId());
    }
}
