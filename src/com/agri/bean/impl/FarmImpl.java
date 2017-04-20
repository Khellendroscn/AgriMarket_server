package com.agri.bean.impl;

import com.agri.bean.Order;
import net.khe.db2.annotations.*;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hyc on 2017/4/4.
 */
@XmlRootElement
@DBTable({"farm"})
public class FarmImpl implements com.agri.bean.Farm {
    @SqlInt
    @Constraints(primaryKey = true,alloNull = false,autoIncrement = true)
    private int id;
    @SqlString(45)
    @Constraints(alloNull = false)
    private String name;
    @SqlString(60)
    private String address;
    @SqlString(16)
    private String tel;
    @SqlText
    private String description;
    @SqlString(100)
    private String imgPath;
    @Container(containerType = ArrayList.class,elementType = ProductImpl.class)
    private List<ProductImpl> productList;
    @Container(containerType = ArrayList.class,elementType = OrderImpl.class)
    private List<Order> orderList;

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getAddress() {
        return address;
    }

    @Override
    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String getTel() {
        return tel;
    }

    @Override
    public void setTel(String tel) {
        this.tel = tel;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public List<ProductImpl> getProductList() {
        return productList;
    }

    @Override
    public void setProductList(List<ProductImpl> productList) {
        this.productList = productList;
    }

    @Override
    public String getImgPath() {
        return imgPath;
    }

    @Override
    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    @Override
    public List<Order> getOrderList() {
        return orderList;
    }

    @Override
    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }
}
