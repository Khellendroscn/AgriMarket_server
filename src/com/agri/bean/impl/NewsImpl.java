package com.agri.bean.impl;

import net.khe.db2.annotations.*;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.Date;

/**
 * Created by hyc on 2017/4/5.
 */
@XmlRootElement
@DBTable({"news"})
public class NewsImpl implements com.agri.bean.News {
    @XmlTransient
    @SqlInt
    @Constraints(primaryKey = true, alloNull = false, autoIncrement = true)
    private int id;
    @SqlString(60)
    @Constraints(alloNull = false)
    private String name;
    @SqlString(100)
    private String imgPath;
    @SqlDate
    private Date createDate;
    @SqlText
    private String content;

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
    public Date getCreateDate() {
        return createDate;
    }

    @Override
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Override
    public String getContent() {
        return content;
    }

    @Override
    public void setContent(String content) {
        this.content = content;
    }
}
