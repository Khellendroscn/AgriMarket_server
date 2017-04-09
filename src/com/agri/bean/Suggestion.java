package com.agri.bean;

/**
 * Created by hyc on 2017/4/9.
 */
public interface Suggestion extends Bean {
    int getId();

    void setId(int id);

    int getCustomerId();

    void setCustomerId(int customerId);

    String getContent();

    void setContent(String content);

    String getTel();

    void setTel(String tel);

    String getEmail();

    void setEmail(String email);
}
