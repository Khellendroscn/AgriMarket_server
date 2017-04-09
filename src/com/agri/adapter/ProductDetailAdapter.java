package com.agri.adapter;

import com.agri.bean.OrderEvaluate;
import com.agri.bean.impl.FarmImpl;
import com.agri.bean.impl.ProductImpl;
import com.myeclipseide.ws.ProductDetail;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by hyc on 2017/4/6.
 */
@XmlRootElement(name = "ProductDetail")
public class ProductDetailAdapter extends ProductDetail {
    public ProductDetailAdapter(){}
    public ProductDetailAdapter(ProductImpl product, FarmImpl farm){
        List<OrderEvaluate> evaluateList = product.getEvaluateList();
        setAvgStar(avgStar(evaluateList));
        setEvaluateCount(evaluateList.size());
        setFarmID(product.getFarmId());
        setFarmName(farm.getName());
        setProductCount(product.getInventory());
        setProductDetailImages(product.getDetailImagePath());
        setProductID(product.getId());
        setProductImage(product.getImagePath());
        setProductName(product.getName());
        setProductPlace(product.getAddress());
        setProductPrice(product.getPrice().floatValue());
        setProductSize(product.getWeight());
    }
    private int avgStar(List<OrderEvaluate> evaluateList){
        int sum = 0;
        for(OrderEvaluate evaluate:evaluateList){
            sum+=evaluate.getStar();
        }
        return evaluateList.size() > 0 ? sum/evaluateList.size() : 0;
    }
}
