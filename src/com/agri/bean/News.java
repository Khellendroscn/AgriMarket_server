package com.agri.bean;

import java.util.Date;

/**
 * Created by hyc on 2017/4/9.
 */
public interface News extends Bean {
    int getId();

    void setId(int id);

    String getName();

    void setName(String name);

    String getImgPath();

    void setImgPath(String imgPath);

    Date getCreateDate();

    void setCreateDate(Date createDate);

    String getContent();

    void setContent(String content);
}
