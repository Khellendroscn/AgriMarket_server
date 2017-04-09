package com.agri.adapter;

import com.agri.bean.Order;
import com.agri.bean.impl.CustomerImpl;

import javax.xml.bind.annotation.XmlRootElement;
import java.text.SimpleDateFormat;

/**
 * Created by hyc on 2017/4/5.
 */
@XmlRootElement(name = "OrderEvaluate")
public class OrderEvaluateAdapter extends com.myeclipseide.ws.OrderEvaluate {
    public OrderEvaluateAdapter(){}
    public OrderEvaluateAdapter(Order order, CustomerImpl customer){
        setCustomerName(customer.getUserName());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = format.format(order.getCreateTime());
        setDateCreate(date);
        date = format.format(order.getEvaluate().getCreateTime());
        setOrderEvaluateDateCreate(date);
        setOrderEvaluateDescription(order.getEvaluate().getContent());
        setStar(order.getEvaluate().getStar());
    }
}
