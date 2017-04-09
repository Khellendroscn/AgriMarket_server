package com.agri.bean;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by hyc on 2017/4/9.
 */
public interface Product extends Bean {
    int getId();

    void setId(int id);

    String getName();

    void setName(String name);

    String getWeight();

    void setWeight(String weight);

    int getInventory();

    void setInventory(int inventory);

    String getDescription();

    void setDescription(String description);

    String getImagePath();

    void setImagePath(String imagePath);

    String getDetailImagePath();

    void setDetailImagePath(String detailImagePath);

    int getRecentSellCount();

    void setRecentSellCount(int recentSellCount);

    BigDecimal getPrice();

    void setPrice(BigDecimal price);

    int getFarmId();

    void setFarmId(int farmId);

    Date getCreateTime();

    void setCreateTime(Date createTime);

    int getClassificationId();

    void setClassificationId(int classificationId);

    List<OrderEvaluate> getEvaluateList();

    void setEvaluateList(List<OrderEvaluate> evaluateList);

    String getAddress();

    void setAddress(String address);
}
