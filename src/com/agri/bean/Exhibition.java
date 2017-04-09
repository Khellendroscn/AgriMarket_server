package com.agri.bean;

import java.util.Date;

/**
 * Created by hyc on 2017/4/9.
 */
public interface Exhibition extends Bean {
    int getId();

    void setId(int id);

    int getProductId();

    void setProductId(int productId);

    Date getCreateTime();

    void setCreateTime(Date createTime);
}
