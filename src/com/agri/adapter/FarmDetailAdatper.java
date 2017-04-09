package com.agri.adapter;

import com.agri.bean.impl.FarmImpl;
import com.myeclipseide.ws.FarmDetail;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by hyc on 2017/4/5.
 */
@XmlRootElement(name = "FarmDetail")
public class FarmDetailAdatper extends FarmDetail {
    public FarmDetailAdatper(){}
    public FarmDetailAdatper(FarmImpl farm){
        setAddress(farm.getAddress());
        setFarmDescription(farm.getDescription());
    }
}
