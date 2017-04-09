package com.agri.bean;

import com.agri.bean.impl.ProductImpl;

import java.util.List;

/**
 * Created by hyc on 2017/4/9.
 */
public interface Farm extends Bean {
    int getId();

    void setId(int id);

    String getName();

    void setName(String name);

    String getAddress();

    void setAddress(String address);

    String getTel();

    void setTel(String tel);

    String getDescription();

    void setDescription(String description);

    List<ProductImpl> getProductList();

    void setProductList(List<ProductImpl> productList);

    String getImgPath();

    void setImgPath(String imgPath);

    List<Order> getOrderList();

    void setOrderList(List<Order> orderList);
}
