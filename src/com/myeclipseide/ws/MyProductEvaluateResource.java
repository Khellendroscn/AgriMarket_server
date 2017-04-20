package com.myeclipseide.ws;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import com.agri.adapter.MyProductEvaluateAdapter;
import com.agri.bean.Order;
import com.agri.bean.impl.OrderImpl;
import com.agri.bean.impl.ProductImpl;
import com.agri.db.DBPoolManager;
import net.khe.db2.DBQuaryException;
import net.khe.db2.DataBase;

@Singleton
@Produces({"application/xml" })
@Path("myproductevaluate")
public class MyProductEvaluateResource {
	private DBPoolManager manager = DBPoolManager.getInstance();
	@GET
	@Path("{customerID}")
	@Produces({"application/xml" })
	public List<MyProductEvaluate> getMyProductsEvaluate(@QueryParam("customerID") int customerID) {
		System.out.println("myProductEvaluate");
		List<MyProductEvaluate> myProductsEvaluate = new ArrayList<MyProductEvaluate>();
		DataBase<Order> dbOrder =
				manager.newDataBase(OrderImpl.class);
		DataBase<ProductImpl> dbProduct =
				manager.newDataBase(ProductImpl.class);
		try {
			dbOrder.select().where("customerId = "+customerID)
                    .query().stream()
                    .forEach(o -> {
                        Order order = (Order)o;
                        try {
                            ProductImpl product = dbProduct.getInstance(order.getProductId());
                            myProductsEvaluate.add(new MyProductEvaluateAdapter(order,product));
                        } catch (DBQuaryException e) {
                            e.printStackTrace();
                        }
                    });
		} catch (DBQuaryException e) {
			e.printStackTrace();
		}finally {
			try {
				dbProduct.close();
				dbOrder.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return myProductsEvaluate;
	}
}
