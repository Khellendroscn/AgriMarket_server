package com.agri.adapter;

import com.agri.bean.impl.ProductImpl;
import com.myeclipseide.ws.ProductOutline;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by hyc on 2017/4/6.
 */
@XmlRootElement(name = "ProductOutline")
public class ProductOutlineAdapter extends ProductOutline {
    public ProductOutlineAdapter(){}
    public ProductOutlineAdapter(ProductImpl product){
        setProductID(product.getId());
        setProductImage(product.getImagePath());
        setProductName(product.getName());
        setProductPrice(product.getPrice().floatValue());
        setRecentSellCount(product.getRecentSellCount());
    }
}
