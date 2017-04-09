package com.agri.adapter;

import com.agri.bean.Order;
import com.myeclipseide.ws.OrderEvaluateAdd;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by hyc on 2017/4/5.
 */
@XmlRootElement(name = "OrderEvaluateAdd")
public class OrderEvaluateAddAdapter extends OrderEvaluateAdd {
    public OrderEvaluateAddAdapter(){}
    public OrderEvaluateAddAdapter(Order order){
        setOrderEvaluateDescription(order.getEvaluate().getContent());
        setOrderID(order.getId());
        setProductID(order.getProductId());
        setStar(order.getEvaluate().getStar());
    }
}
