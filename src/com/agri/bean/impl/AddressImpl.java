package com.agri.bean.impl;

import net.khe.db2.annotations.*;

/**
 * Created by hyc on 2017/4/3.
 */
@DBTable({"address"})
public class AddressImpl implements com.agri.bean.Address {
    @Constraints(primaryKey = true,alloNull = false,autoIncrement = true)
    @SqlInt
    private int id;
    @SqlString(40)
    @Constraints(alloNull = false)
    private String name;
    @Foreign(CustomerInfoImpl.class)
    @SqlInt
    @Constraints(alloNull = false)
    private int customerId;
    @SqlString(60)
    @Constraints(alloNull = false)
    private String content;
    @SqlString(16)
    @Constraints(alloNull = false)
    private String tel;

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
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }
}
