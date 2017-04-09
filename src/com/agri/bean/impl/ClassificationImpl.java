package com.agri.bean.impl;

import net.khe.db2.annotations.*;

/**
 * Created by hyc on 2017/4/5.
 */
@DBTable({"classification"})
public class ClassificationImpl implements com.agri.bean.Classification {
    @SqlInt
    @Constraints(primaryKey = true, alloNull = false, autoIncrement = true)
    private int id;
    @SqlInt
    @Constraints(alloNull = false)
    @Foreign(FClassificationImpl.class)
    private int fClassificationId;
    @SqlString(20)
    @Constraints(alloNull = false)
    private String name;

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int getFClassificationId() {
        return fClassificationId;
    }

    @Override
    public void setFClassificationId(int fClassifictionId) {
        this.fClassificationId = fClassifictionId;
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
