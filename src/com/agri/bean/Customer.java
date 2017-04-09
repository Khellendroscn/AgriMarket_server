package com.agri.bean;

import com.agri.bean.impl.CustomerImpl;
import com.agri.bean.impl.CustomerInfoImpl;
import com.agri.bean.impl.OrderImpl;
import com.agri.bean.impl.ShoppingCartItemImpl;
import com.agri.db.DBPoolManager;
import net.khe.db2.DBWriteException;
import net.khe.db2.DataBase;

import java.util.List;

/**
 * Created by hyc on 2017/4/9.
 */
public interface Customer extends Bean {

    int getId();

    void setId(int id);

    String getUserName();

    void setUserName(String userName);

    String getPassword();

    void setPassword(String password);

    CustomerInfoImpl getInfo();

    void setInfo(CustomerInfoImpl info);

    List<ShoppingCartItemImpl> getShoppingCart();

    void setShoppingCart(List<ShoppingCartItemImpl> shoppingCart);

    List<OrderImpl> getOrderList();

    void setOrderList(List<OrderImpl> orderList);

    String getStatus();

    void setStatus(String status);
}
