package com.agri.adapter;

import com.agri.bean.impl.FClassificationImpl;
import com.myeclipseide.ws.FClassification;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by hyc on 2017/4/5.
 */
@XmlRootElement(name = "FClassification")
public class FClassificationAdapter extends FClassification {
    public FClassificationAdapter(){}
    public FClassificationAdapter(FClassificationImpl fClassification){
        setFClassificationDescription(fClassification.getDescription());
        setFClassificationID(fClassification.getId());
        setFClassificationImage(fClassification.getImgPath());
        setFClassificationName(fClassification.getName());
    }
}
