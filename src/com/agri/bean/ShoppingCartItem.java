package com.agri.bean;

import java.util.Date;

/**
 * Created by hyc on 2017/4/9.
 */
public interface ShoppingCartItem extends Bean {
    int getId();

    void setId(int id);

    int getCustomerId();

    void setCustomerId(int customerId);

    int getProductId();

    void setProductId(int productId);

    Date getCreateTime();

    void setCreateTime(Date createTime);

    int getAmount();

    void setAmount(int amount);
}
