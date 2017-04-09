package com.agri.adapter;

import com.agri.bean.impl.NewsImpl;
import com.myeclipseide.ws.NewDescription;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by hyc on 2017/4/5.
 */
@XmlRootElement(name = "NewDescription")
public class NewDescriptionAdapter extends NewDescription {
    public NewDescriptionAdapter(){}
    public NewDescriptionAdapter(NewsImpl news){
        setNewDetail(news.getContent());
        setNewImage(news.getImgPath());
    }
}
