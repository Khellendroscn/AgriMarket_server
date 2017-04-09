package com.agri.bean;

import java.util.Date;

/**
 * Created by hyc on 2017/4/9.
 */
public interface OrderEvaluate extends Bean {
    int getOrderId();

    void setOrderId(int orderId);

    int getStar();

    void setStar(int star);

    String getContent();

    void setContent(String content);

    Date getCreateTime();

    void setCreateTime(Date createTime);

    int getProductId();

    void setProductId(int productId);
}
