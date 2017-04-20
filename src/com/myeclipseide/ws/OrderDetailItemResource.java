package com.myeclipseide.ws;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import com.agri.adapter.OrderDetailItemAdapter;
import com.agri.bean.Order;
import com.agri.bean.impl.OrderImpl;
import com.agri.bean.impl.ProductImpl;
import com.agri.db.DBPoolManager;
import net.khe.db2.DBQuaryException;
import net.khe.db2.DataBase;

@Singleton
@Produces({"application/xml" })
@Path("orderoutline/orderdetailitem")
public class OrderDetailItemResource {
	private DBPoolManager manager = DBPoolManager.getInstance();
	@Path("/query")
	@GET
	@Produces({"application/xml" })
	public List<OrderDetailItem> getOrderDetailItems(@QueryParam("orderID") int cOrderID,@QueryParam("farmID") int cFarmID) {
		List<OrderDetailItem> orderDetailItems = new ArrayList<OrderDetailItem>();
		DataBase<Order> dbOrder = manager.newDataBase(OrderImpl.class);
		DataBase<ProductImpl> dbProduct = manager.newDataBase(ProductImpl.class);
		try {
			dbOrder.select().where("id = "+cOrderID,"farmId = "+cFarmID)
                    .query().stream()
                    .forEach(o -> {
                        Order order = (Order) o;
                        try {
                            ProductImpl product = dbProduct.getInstance(order.getProductId());
                            orderDetailItems.add(new OrderDetailItemAdapter(order,product));
                        } catch (DBQuaryException e) {
                            e.printStackTrace();
                        }
                    });
		} catch (DBQuaryException e) {
			e.printStackTrace();
		}finally {
			try {
				dbOrder.close();
				dbProduct.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return orderDetailItems;
	}
}
