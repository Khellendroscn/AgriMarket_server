package com.myeclipseide.ws;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import com.agri.adapter.OrderEvaluateAdapter;
import com.agri.bean.Order;
import com.agri.bean.impl.CustomerImpl;
import com.agri.bean.impl.OrderImpl;
import com.agri.db.DBPoolManager;
import net.khe.db2.DBQuaryException;
import net.khe.db2.DataBase;

@Produces({"application/xml" })
@Path("orderevaluate")
public class OrderEvaluateResource {
	private DBPoolManager manager = DBPoolManager.getInstance();
	@Path("{productID}")
	@GET
	@Produces({"application/xml" })
	public List<OrderEvaluate> getProductEvaluate(@QueryParam("productID") int productID) {
		List<OrderEvaluate> orderEvaluates = new ArrayList<OrderEvaluate>();
		DataBase<Order> dbOrder = manager.newDataBase(OrderImpl.class);
		DataBase<CustomerImpl> dbCustomer = manager.newDataBase(CustomerImpl.class);
		try {
			dbOrder.select().where("productId = "+productID)
                    .query().stream()
					.filter(o -> {
						Order order = (Order) o;
						return order.getEvaluate()!=null;
					})
                    .forEach(o -> {
                        Order order = (Order) o;
                        try {
                            CustomerImpl customer = dbCustomer.getInstance(order.getCustomerId());
                            orderEvaluates.add(new OrderEvaluateAdapter(order,customer));
                        } catch (DBQuaryException e) {
                            e.printStackTrace();
                        }
                    });
		} catch (DBQuaryException e) {
			e.printStackTrace();
		} finally {
			try {
				dbCustomer.close();
				dbOrder.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return orderEvaluates;
	}
}
