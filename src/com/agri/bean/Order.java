package com.agri.bean;

import com.agri.bean.impl.OrderEvaluateImpl;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by hyc on 2017/4/9.
 */
public interface Order extends Bean {
    int getId();

    void setId(int id);

    int getCustomerId();

    void setCustomerId(int customerId);

    int getProductId();

    void setProductId(int productId);

    int getProductAmount();

    void setProductAmount(int productAmount);

    int getFarmId();

    void setFarmId(int farmId);

    BigDecimal getPrice();

    void setPrice(BigDecimal price);

    OrderEvaluate getEvaluate();

    void setEvaluate(OrderEvaluateImpl evaluate);

    int getAddressId();

    void setAddressId(int addressId);

    Date getCreateTime();

    void setCreateTime(Date createTime);

    String getStatus();

    void setStatus(String status);
}
