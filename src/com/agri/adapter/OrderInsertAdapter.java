package com.agri.adapter;

import com.agri.bean.Order;
import com.myeclipseide.ws.OrderInsert;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by hyc on 2017/4/5.
 */
@XmlRootElement(name = "OrderInsert")
public class OrderInsertAdapter extends OrderInsert {
    public OrderInsertAdapter(){}
    public OrderInsertAdapter(Order order){
        setAddressID(order.getAddressId());
        setCustomerID(order.getCustomerId());
        setSum(order.getPrice().floatValue());
    }
}
