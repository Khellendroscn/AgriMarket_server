package com.agri.bean;

import java.util.Date;

/**
 * Created by hyc on 2017/4/9.
 */
public interface CustomerService extends Bean {
    int getId();

    void setId(int id);

    int getCustomerId();

    void setCustomerId(int customerId);

    String getQuestion();

    void setQuestion(String question);

    String getEmail();

    void setEmail(String email);

    String getTel();

    void setTel(String tel);

    Date getCreateTime();

    void setCreateTime(Date createTime);
}
