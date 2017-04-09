package com.agri.bean.impl;

import com.agri.bean.OrderEvaluate;
import net.khe.db2.annotations.*;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by hyc on 2017/4/4.
 */
@DBTable({"order_"})
public class OrderImpl implements com.agri.bean.Order {
    @SqlInt
    @Constraints(primaryKey = true,alloNull = false,autoIncrement = true)
    private int id;
    @SqlInt
    @Constraints(alloNull = false)
    @Foreign(CustomerImpl.class)
    private int customerId;
    @SqlInt
    @Constraints(alloNull = false)
    private int productId;
    @SqlInt
    @Constraints(alloNull = false)
    private int productAmount;
    @SqlInt
    @Constraints(alloNull = false)
    @Foreign(FarmImpl.class)
    private int farmId;
    @SqlDecimal(size = 9,d = 2)
    @Constraints(alloNull = false)
    private BigDecimal price;
    @SqlInt
    @Constraints(alloNull = false)
    private int addressId;
    @SqlDate
    private Date createTime;
    @SqlString(20)
    @Constraints(alloNull = false)
    private String status;
    private OrderEvaluateImpl evaluate;

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
    public int getProductAmount() {
        return productAmount;
    }

    @Override
    public void setProductAmount(int productAmount) {
        this.productAmount = productAmount;
    }

    @Override
    public int getFarmId() {
        return farmId;
    }

    @Override
    public void setFarmId(int farmId) {
        this.farmId = farmId;
    }

    @Override
    public BigDecimal getPrice() {
        return price;
    }

    @Override
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public OrderEvaluate getEvaluate() {
        return evaluate;
    }

    @Override
    public void setEvaluate(OrderEvaluateImpl evaluate) {
        this.evaluate = evaluate;
    }

    @Override
    public int getAddressId() {
        return addressId;
    }

    @Override
    public void setAddressId(int addressId) {
        this.addressId = addressId;
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
    public String getStatus() {
        return status;
    }

    @Override
    public void setStatus(String status) {
        this.status = status;
    }
}
