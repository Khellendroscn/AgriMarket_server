package com.agri.adapter;

import com.agri.bean.impl.NewsImpl;
import com.myeclipseide.ws.NewOutline;

import javax.xml.bind.annotation.XmlRootElement;
import java.text.SimpleDateFormat;

/**
 * Created by hyc on 2017/4/5.
 */
@XmlRootElement(name = "NewOutline")
public class NewOutlineAdapter extends NewOutline {
    public NewOutlineAdapter(){}
    public NewOutlineAdapter(NewsImpl news){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = format.format(news.getCreateDate());
        setNewDate(date);
        setNewID(news.getId());
        setNewImage(news.getImgPath());
        setNewName(news.getName());
    }
}
