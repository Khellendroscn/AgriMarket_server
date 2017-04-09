package com.agri.adapter;

import com.agri.bean.Order;
import com.agri.bean.impl.AddressImpl;
import com.agri.bean.impl.FarmImpl;
import com.myeclipseide.ws.OrderDetail;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by hyc on 2017/4/5.
 */
@XmlRootElement(name = "OrderDetail")
public class OrderDetailAdapter extends OrderDetail {
    public OrderDetailAdapter(){}
    public OrderDetailAdapter(Order order, FarmImpl farm, AddressImpl addr){
        setAddress(addr.getContent());
        setFarmID(order.getFarmId());
        setFarmName(farm.getName());
        setOrderID(order.getId());
        setStatus(order.getStatus());
        setTelNumber(addr.getTel());
        setDateCreate(order.getCreateTime().getTime());
        setName(addr.getName());
    }
}
