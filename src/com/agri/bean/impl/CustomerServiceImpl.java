package com.agri.bean.impl;

import net.khe.db2.annotations.*;

import java.util.Date;

/**
 * Created by hyc on 2017/4/5.
 */
@DBTable({"customer_service"})
public class CustomerServiceImpl implements com.agri.bean.CustomerService {
    @SqlInt
    @Constraints(primaryKey = true, alloNull = false, autoIncrement = true)
    private int id;
    @SqlInt
    @Constraints(alloNull = false)
    private int customerId;
    @SqlText
    @Constraints(alloNull = false)
    private String question;
    @SqlString(40)
    private String email;
    @SqlString(11)
    private String tel;
    @SqlDate
    private Date createTime;

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
    public String getQuestion() {
        return question;
    }

    @Override
    public void setQuestion(String question) {
        this.question = question;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getTel() {
        return tel;
    }

    @Override
    public void setTel(String tel) {
        this.tel = tel;
    }

    @Override
    public Date getCreateTime() {
        return createTime;
    }

    @Override
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
