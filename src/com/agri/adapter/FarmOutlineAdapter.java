package com.agri.adapter;

import com.agri.bean.impl.FarmImpl;
import com.myeclipseide.ws.FarmOutline;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by hyc on 2017/4/5.
 */
@XmlRootElement(name = "FarmOutline")
public class FarmOutlineAdapter extends FarmOutline {
    public FarmOutlineAdapter(){}
    public FarmOutlineAdapter(FarmImpl farm){
        setFarmID(farm.getId());
        setFarmImage(farm.getImgPath());
        setFarmName(farm.getName());
        setTelNumber(farm.getTel());
    }
}
