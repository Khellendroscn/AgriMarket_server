package com.agri.adapter;

import com.agri.bean.Order;
import com.agri.bean.impl.FarmImpl;
import com.agri.bean.impl.ProductImpl;
import com.myeclipseide.ws.OrderOutline;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by hyc on 2017/4/5.
 */
@XmlRootElement(name = "OrderOutline")
public class OrderOutlineAdapter extends OrderOutline {
    public OrderOutlineAdapter(){}
    public OrderOutlineAdapter(Order order, FarmImpl farm, ProductImpl product){
        setFarmID(order.getFarmId());
        setFarmName(farm.getName());
        setOrderID(order.getId());
        setProductCount(order.getProductAmount());
        setProductID(order.getProductId());
        setProductImage(product.getImagePath());
        setProductName(product.getName());
        setSum(order.getPrice().floatValue());
    }
}
