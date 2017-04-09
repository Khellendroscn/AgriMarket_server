package com.agri.bean.impl;

import com.agri.bean.OrderEvaluate;
import net.khe.db2.annotations.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by hyc on 2017/4/3.
 */
@DBTable({"product"})
public class ProductImpl implements com.agri.bean.Product {
    @SqlInt
    @Constraints(primaryKey = true, alloNull = false, autoIncrement = true)
    private int id;
    @SqlString(60)
    private String name;
    @SqlDecimal(size = 9,d = 2)
    private BigDecimal price;
    @SqlInt
    private int classificationId;
    @SqlString
    private String weight;
    @SqlInt
    private int inventory;
    @SqlText
    private String description;
    @SqlString(100)
    private String imagePath;
    @SqlString(500)
    private String detailImagePath;
    @SqlInt
    private int recentSellCount;
    @SqlInt
    @Foreign(FarmImpl.class)
    private int farmId;
    @SqlDate
    private Date createTime;
    @SqlString(20)
    private String address;
    @Container(elementType = OrderEvaluateImpl.class,containerType = ArrayList.class)
    private List<OrderEvaluate> evaluateList;

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
    public String getWeight() {
        return weight;
    }

    @Override
    public void setWeight(String weight) {
        this.weight = weight;
    }

    @Override
    public int getInventory() {
        return inventory;
    }

    @Override
    public void setInventory(int inventory) {
        this.inventory = inventory;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String getImagePath() {
        return imagePath;
    }

    @Override
    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    @Override
    public String getDetailImagePath() {
        return detailImagePath;
    }

    @Override
    public void setDetailImagePath(String detailImagePath) {
        this.detailImagePath = detailImagePath;
    }

    @Override
    public int getRecentSellCount() {
        return recentSellCount;
    }

    @Override
    public void setRecentSellCount(int recentSellCount) {
        this.recentSellCount = recentSellCount;
    }

    @Override
    public BigDecimal getPrice() {
        return price;
    }

    @Override
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public int getFarmId() {
        return farmId;
    }

    @Override
    public void setFarmId(int farmId) {
        this.farmId = farmId;
    }

    @Override
    public Date getCreateTime() {
        return createTime;
    }

    @Override
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public int getClassificationId() {
        return classificationId;
    }

    @Override
    public void setClassificationId(int classificationId) {
        this.classificationId = classificationId;
    }

    @Override
    public List<OrderEvaluate> getEvaluateList() {
        return evaluateList;
    }

    @Override
    public void setEvaluateList(List<OrderEvaluate> evaluateList) {
        this.evaluateList = evaluateList;
    }

    @Override
    public String getAddress() {
        return address;
    }

    @Override
    public void setAddress(String address) {
        this.address = address;
    }
}
