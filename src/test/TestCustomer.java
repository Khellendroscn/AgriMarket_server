package test;

import com.agri.bean.impl.CustomerImpl;
import com.agri.bean.impl.ShoppingCartItemImpl;
import com.agri.db.DBPoolManager;
import com.agri.db.DBSyncArrayList;
import net.khe.db2.DataBase;

import java.util.Date;
import java.util.List;

/**
 * Created by hyc on 2017/4/5.
 */
public class TestCustomer {
    public static void main(String[] args) {
        DBPoolManager manager = DBPoolManager.getInstance();
        DataBase<CustomerImpl> db = manager.newDataBase(CustomerImpl.class);
        try {
            CustomerImpl user = db.getInstance(1);
            List<ShoppingCartItemImpl> cart = new DBSyncArrayList<>(user.getShoppingCart());
            ShoppingCartItemImpl item = new ShoppingCartItemImpl();
            item.setCreateTime(new Date());
            item.setCustomerId(user.getId());
            item.setProductId(1);
            cart.add(item);
            db.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
