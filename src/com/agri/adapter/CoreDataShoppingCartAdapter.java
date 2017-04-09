package com.agri.adapter;

import com.agri.bean.impl.ShoppingCartItemImpl;
import com.myeclipseide.ws.CoreDataShoppingCart;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by hyc on 2017/4/5.
 */
@XmlRootElement(name = "CoreDataShoppingCart")
public class CoreDataShoppingCartAdapter extends CoreDataShoppingCart {
    public CoreDataShoppingCartAdapter(){}
    public CoreDataShoppingCartAdapter(ShoppingCartItemImpl item){
        setProductCount(item.getAmount());
        setProductID(item.getProductId());
    }
}
