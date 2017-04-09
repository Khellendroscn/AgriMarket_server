package com.agri.adapter;

import com.agri.bean.impl.ProductImpl;
import com.myeclipseide.ws.ProductDescription;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by hyc on 2017/4/5.
 */
@XmlRootElement(name = "ProductDescription")
public class ProductDescriptionAdapter extends ProductDescription {
    public ProductDescriptionAdapter(){}
    public ProductDescriptionAdapter(ProductImpl product){
        setProductDescriptionString(product.getDescription());
        setProductName(product.getName());
    }
}
