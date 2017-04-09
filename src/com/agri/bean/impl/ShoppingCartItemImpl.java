package com.agri.bean.impl;

import net.khe.db2.annotations.*;

import java.util.Date;

/**
 * Created by hyc on 2017/4/5.
 */
@DBTable({"shoppingcart"})
public class ShoppingCartItemImpl implements com.agri.bean.ShoppingCartItem {
    @SqlInt
    @Constraints(primaryKey = true, alloNull = false, autoIncrement = true)
    private int id;
    @SqlInt
    @Foreign(CustomerImpl.class)
    @Constraints(alloNull = false)
    private int customerId;
    @SqlInt
    @Constraints(alloNull = false)
    private int productId;
    @SqlDate
    private Date createTime;
    @SqlInt
    @Constraints(alloNull = false)
    private int amount;

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int getCustomerId() {
        return customerId;
    }

    @Override
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    @Override
    public int getProductId() {
        return productId;
    }

    @Override
    public void setProductId(int productId) {
        this.productId = productId;
    }

    @Override
    public Date getCreateTime() {
        return createTime;
    }

    @Override
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public int getAmount() {
        return amount;
    }

    @Override
    public void setAmount(int amount) {
        this.amount = amount;
    }
}
