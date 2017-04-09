package com.myeclipseide.ws;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import com.agri.adapter.OrderOutlineAdapter;
import com.agri.bean.Order;
import com.agri.bean.impl.FarmImpl;
import com.agri.bean.impl.OrderImpl;
import com.agri.bean.impl.ProductImpl;
import com.agri.db.DBPoolManager;
import net.khe.db2.DBQuaryException;
import net.khe.db2.DataBase;

@Produces({"application/xml" })
@Path("orderoutline")
public class OrderOutlineResource {
	private DBPoolManager manager = DBPoolManager.getInstance();
	@Path("{customerID}")
	@GET
	@Produces({"application/xml" })
	public List<OrderOutline> getOrderOutlineItems(@QueryParam("customerID") int customerID) {
		List<OrderOutline> orderOutlineItems = new ArrayList<OrderOutline>();
		DataBase<Order> dbOrder = manager.newDataBase(OrderImpl.class);
		DataBase<FarmImpl> dbFarm = manager.newDataBase(FarmImpl.class);
		DataBase<ProductImpl> dbProduct = manager.newDataBase(ProductImpl.class);
		try {
			dbOrder.select().where("customerId = "+customerID)
                    .query().stream()
                    .forEach(o -> {
                        Order order = (Order) o;
                        try {
                            FarmImpl farm = dbFarm.getInstance(order.getFarmId());
                            ProductImpl product = dbProduct.getInstance(order.getProductId());
                            orderOutlineItems.add(new OrderOutlineAdapter(order,farm,product));
                        } catch (DBQuaryException e) {
                            e.printStackTrace();
                        }
                    });
		} catch (DBQuaryException e) {
			e.printStackTrace();
		}finally {
			try {
				dbFarm.close();
				dbOrder.close();
				dbProduct.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return orderOutlineItems;
	}
}
