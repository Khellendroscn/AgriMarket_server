package com.agri.adapter;

import com.agri.bean.Order;
import com.myeclipseide.ws.OrderInsertItem;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by hyc on 2017/4/5.
 */
@XmlRootElement(name = "OrderInsertItem")
public class OrderInsertItemAdapter extends OrderInsertItem {
    public OrderInsertItemAdapter(){}
    public OrderInsertItemAdapter(Order order){
        setFarmID(order.getFarmId());
        setOrderID(order.getId());
        setPartSum(order.getPrice().floatValue());
        setProductCount(order.getProductAmount());
        setProductID(order.getProductId());
    }
}
