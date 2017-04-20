package com.agri.bean.impl;

import net.khe.db2.annotations.Constraints;
import net.khe.db2.annotations.DBTable;
import net.khe.db2.annotations.SqlDate;
import net.khe.db2.annotations.SqlInt;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

/**
 * Created by hyc on 2017/4/5.
 */
@XmlRootElement
@DBTable({"exihibition"})
public class ExhibitionImpl implements com.agri.bean.Exhibition {
    @SqlInt
    @Constraints(primaryKey = true, alloNull = false, autoIncrement = true)
    private int id;
    @SqlInt
    @Constraints(alloNull = false)
    private int productId;
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
    public int getProductId() {
        return productId;
    }

    @Override
    public void setProductId(int productId) {
        this.productId = productId;
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
