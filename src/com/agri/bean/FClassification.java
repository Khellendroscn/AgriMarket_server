package com.agri.bean;

/**
 * Created by hyc on 2017/4/9.
 */
public interface FClassification extends Bean {
    int getId();

    void setId(int id);

    String getName();

    void setName(String name);

    String getImgPath();

    void setImgPath(String imgPath);

    String getDescription();

    void setDescription(String description);
}
