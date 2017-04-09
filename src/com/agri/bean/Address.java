package com.agri.bean;

/**
 * Created by hyc on 2017/4/9.
 */
public interface Address extends Bean {
    int getId();

    void setId(int id);

    int getCustomerId();

    void setCustomerId(int customerId);

    String getContent();

    void setContent(String content);

    String getTel();

    void setTel(String tel);

    String getName();

    void setName(String name);
}
