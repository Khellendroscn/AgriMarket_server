package com.agri.adapter;

import com.agri.bean.impl.ExhibitionImpl;
import com.agri.bean.impl.ProductImpl;
import com.myeclipseide.ws.Exhibition;

import javax.xml.bind.annotation.XmlRootElement;
import java.text.SimpleDateFormat;

/**
 * Created by hyc on 2017/4/5.
 */
@XmlRootElement(name = "Exhibition")
public class ExhibitionAdapter extends Exhibition {
    public ExhibitionAdapter(){}
    public ExhibitionAdapter(ExhibitionImpl exhibition, ProductImpl product){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = format.format(exhibition.getCreateTime());
        setDateCreated(date);
        setExhibitionID(exhibition.getId());
        setProductID(product.getId());
        setProductName(product.getName());
        setProductImage(product.getImagePath());
    }
}
