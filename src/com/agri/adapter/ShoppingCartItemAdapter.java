package com.agri.adapter;

import com.agri.bean.impl.FarmImpl;
import com.agri.bean.impl.ProductImpl;
import com.myeclipseide.ws.ShoppingCartItem;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by hyc on 2017/4/6.
 */
@XmlRootElement(name = "ShoppingCartItem")
public class ShoppingCartItemAdapter extends ShoppingCartItem {
    public ShoppingCartItemAdapter(){}
    public ShoppingCartItemAdapter(ProductImpl product, FarmImpl farm){
        setFarmID(product.getFarmId());
        setFarmName(farm.getName());
        setProductID(product.getId());
        setProductImage(product.getImagePath());
        setProductName(product.getName());
        setProductPrice(product.getPrice().floatValue());
    }
}
