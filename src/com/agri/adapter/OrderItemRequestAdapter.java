package com.agri.adapter;

import com.agri.bean.Order;
import com.myeclipseide.ws.OrderItemRequest;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by hyc on 2017/4/5.
 */
@XmlRootElement(name = "OrderItemRequest")
public class OrderItemRequestAdapter extends OrderItemRequest {
    public OrderItemRequestAdapter(){}
    public OrderItemRequestAdapter(Order order){
        setProductCount(order.getProductAmount());
        setProductID(order.getProductId());
    }
}
