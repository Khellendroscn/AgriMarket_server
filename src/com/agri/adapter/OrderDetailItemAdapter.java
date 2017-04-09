package com.agri.adapter;

import com.agri.bean.Order;
import com.agri.bean.impl.ProductImpl;
import com.myeclipseide.ws.OrderDetailItem;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by hyc on 2017/4/5.
 */
@XmlRootElement(name = "OrderDetailItem")
public class OrderDetailItemAdapter extends OrderDetailItem {
    public OrderDetailItemAdapter(){}
    public OrderDetailItemAdapter(Order order, ProductImpl product){
        setProductCount(order.getProductAmount());
        setProductID(product.getId());
        setProductImage(product.getImagePath());
        setProductName(product.getName());
        setSum(order.getPrice().floatValue());
    }
}
