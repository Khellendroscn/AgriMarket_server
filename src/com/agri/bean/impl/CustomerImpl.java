package com.agri.bean.impl;

import net.khe.db2.annotations.*;
import javax.xml.bind.annotation.XmlTransient;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hyc on 2017/4/3.
 */
@DBTable({"customer"})
public class CustomerImpl implements com.agri.bean.Customer {
    @SqlInt
    @Constraints(primaryKey = true,alloNull = false,autoIncrement = true)
    @XmlTransient
    private int id;
    @SqlString(20)
    @Constraints(unique = true,alloNull = false)
    private String userName;
    @SqlString(32)
    @Constraints(alloNull = false)
    private String password;
    @SqlChars(1)
    @Constraints(alloNull = false)
    private String status;
    private CustomerInfoImpl info;
    @Container(containerType = ArrayList.class,elementType = ShoppingCartItemImpl.class)
    private List<ShoppingCartItemImpl> shoppingCart;
    @Container(containerType = ArrayList.class,elementType = OrderImpl.class)
    private List<OrderImpl> orderList;

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getUserName() {
        return userName;
    }

    @Override
    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public CustomerInfoImpl getInfo() {
        return info;
    }

    @Override
    public void setInfo(CustomerInfoImpl info) {
        this.info = info;
    }

    @Override
    public List<ShoppingCartItemImpl> getShoppingCart() {
        return shoppingCart;
    }

    @Override
    public void setShoppingCart(List<ShoppingCartItemImpl> shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    @Override
    public List<OrderImpl> getOrderList() {
        return orderList;
    }

    @Override
    public void setOrderList(List<OrderImpl> orderList) {
        this.orderList = orderList;
    }

    @Override
    public String getStatus() {
        return status;
    }

    @Override
    public void setStatus(String status) {
        this.status = status;
    }
}
