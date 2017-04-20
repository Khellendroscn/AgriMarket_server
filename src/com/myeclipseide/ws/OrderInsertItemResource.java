package com.myeclipseide.ws;

import java.math.BigDecimal;
import java.sql.*;
import java.util.Date;
import java.util.List;

import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.agri.bean.Order;
import com.agri.bean.impl.OrderImpl;
import com.agri.db.DBPoolManager;
import net.khe.db2.DBQuaryException;
import net.khe.db2.DBSession;
import net.khe.db2.DBWriteException;
import net.khe.db2.DataBase;

@Singleton
@Produces({"application/xml" })
@Path("orderinsertitem")
public class OrderInsertItemResource {
	private DBPoolManager manager = DBPoolManager.getInstance();
	@POST
	@Path("add")
	@Produces("application/xml")
	@Consumes("text/xml")
	public void addOrderInsertItem(List<OrderInsertItem> orderInsertItems) {
		DataBase<Order> db = manager.newDataBase(OrderImpl.class);
		DBSession<Order> session = db.createSession();
		for(OrderInsertItem item:orderInsertItems){
			Order order = null;
			try {
				order = db.getInstance(item.getOrderID());
			} catch (DBQuaryException e) {
				e.printStackTrace();
			}
			if(order==null){
				order = new OrderImpl();
				order.setId(item.getOrderID());
			}
			order.setProductId(item.getProductID());
			order.setProductAmount(item.getProductCount());
			order.setPrice(new BigDecimal(item.getPartSum()));
			order.setCreateTime(new Date());
			order.setFarmId(item.getFarmID());
			order.setStatus("ÒÑÏÂµ¥");
			session.put(order);
			ShoppingCartResource resource = new ShoppingCartResource();
			resource.deleteShoppingCartItem(order.getProductId(),order.getCustomerId());
		}
		try {
			session.execute();
		} catch (DBWriteException e) {
			e.printStackTrace();
		}finally {
			try {
				db.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
