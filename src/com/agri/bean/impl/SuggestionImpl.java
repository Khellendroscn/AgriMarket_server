package com.agri.bean.impl;

import net.khe.db2.annotations.*;

/**
 * Created by hyc on 2017/4/5.
 */
@DBTable({"suggestion"})
public class SuggestionImpl implements com.agri.bean.Suggestion {
    @SqlInt
    @Constraints(primaryKey = true, alloNull = false, autoIncrement = true)
    private int id;
    @SqlInt
    @Constraints(alloNull = false)
    private int customerId;
    @SqlText
    @Constraints(alloNull = false)
    private String content;
    @SqlString(50)
    private String tel;
    @SqlString(50)
    private String email;

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
    public String getContent() {
        return content;
    }

    @Override
    public void setContent(String content) {
        this.content = content;
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
    public String getEmail() {
        return email;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }
}
