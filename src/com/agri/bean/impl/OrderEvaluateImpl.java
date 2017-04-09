package com.agri.bean.impl;

import net.khe.db2.annotations.*;

import java.util.Date;

/**
 * Created by hyc on 2017/4/5.
 */
@DBTable({"order_evaluate"})
public class OrderEvaluateImpl implements com.agri.bean.OrderEvaluate {
    @SqlInt
    @Constraints(primaryKey = true, alloNull = false)
    @Foreign(OrderImpl.class)
    private int orderId;
    @SqlInt
    @Constraints(alloNull = false)
    @Foreign(ProductImpl.class)
    private int productId;
    @SqlInt
    @Constraints(alloNull = false)
    private int star;
    @SqlText
    private String content;
    @SqlDate
    private Date createTime;

    @Override
    public int getOrderId() {
        return orderId;
    }

    @Override
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    @Override
    public int getStar() {
        return star;
    }

    @Override
    public void setStar(int star) {
        this.star = star;
    }

    @Override
    public String getContent() {
        return content;
    }

    @Override
    public void setContent(String content) {
        this.content = content;
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
    public int getProductId() {
        return productId;
    }

    @Override
    public void setProductId(int productId) {
        this.productId = productId;
    }
}
