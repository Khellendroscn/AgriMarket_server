package com.agri.bean;

/**
 * Created by hyc on 2017/4/9.
 */
public interface Classification extends Bean {
    int getId();

    void setId(int id);

    int getFClassificationId();

    void setFClassificationId(int fClassifictionId);

    String getName();

    void setName(String name);
}
