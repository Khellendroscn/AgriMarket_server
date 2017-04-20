package com.agri.bean.impl;

import net.khe.db2.annotations.Constraints;
import net.khe.db2.annotations.DBTable;
import net.khe.db2.annotations.SqlInt;
import net.khe.db2.annotations.SqlString;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by hyc on 2017/4/5.
 */
@XmlRootElement
@DBTable({"fclassification"})
public class FClassificationImpl implements com.agri.bean.FClassification {
    @SqlInt
    @Constraints(primaryKey = true,alloNull = false,autoIncrement = true)
    private int id;
    @SqlString(20)
    @Constraints(alloNull = false)
    private String name;
    @SqlString(100)
    private String imgPath;
    @SqlString(50)
    private String description;

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getImgPath() {
        return imgPath;
    }

    @Override
    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }
}
