package com.agri.adapter;

import com.agri.bean.Order;
import com.agri.bean.OrderEvaluate;
import com.agri.bean.impl.OrderEvaluateImpl;
import com.agri.bean.impl.ProductImpl;
import com.myeclipseide.ws.MyProductEvaluate;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by hyc on 2017/4/5.
 */
@XmlRootElement(name = "MyProductEvaluate")
public class MyProductEvaluateAdapter extends MyProductEvaluate {
    public MyProductEvaluateAdapter(){}
    public MyProductEvaluateAdapter(Order order, ProductImpl product){
        OrderEvaluate evaluate = order.getEvaluate();
        if(evaluate==null){
            evaluate = new OrderEvaluateImpl();
            evaluate.setOrderId(order.getId());
            order.setEvaluate((OrderEvaluateImpl) evaluate);
            setEvaluateOrNot("0");
        }else{
            setEvaluateOrNot("1");
        }
        setOrderID(evaluate.getOrderId());
        setProductID(order.getProductId());
        setProductName(product.getName());
        setProductImage(product.getImagePath());
    }
}
