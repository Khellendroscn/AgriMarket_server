package com.myeclipseide.ws;

import java.math.BigDecimal;
import java.sql.*;
import java.util.Date;

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

@Produces({"application/xml" })
@Path("orderinsert")
public class OrderInsertResource {
	private DBPoolManager manager = DBPoolManager.getInstance();
	@POST
	@Path("add")
	@Produces("application/xml")
	@Consumes("text/xml")
	public key addOrderInsert(OrderInsert orderInsert) {
		key primaryKey = new key();
		Order order = new OrderImpl();
		order.setCustomerId(orderInsert.getCustomerID());
		order.setAddressId(orderInsert.getAddressID());
		order.setProductId(-1);
		order.setStatus("ÒÑÏÂµ¥");
		order.setFarmId(-1);
		order.setCreateTime(new Date());
		order.setPrice(new BigDecimal(orderInsert.getSum()));
		order.setProductAmount(-1);
		DataBase<Order> db = manager.newDataBase(OrderImpl.class);
		DBSession<Order> session = db.createSession();
		session.put(order);
		try {
			session.execute();
			int index = db.query().stream()
					.map(order1 -> order1.getId())
					.max(Integer::compareTo).get();
			primaryKey.setPrimaryKey(index);
		} catch (DBWriteException e) {
			e.printStackTrace();
		} catch (DBQuaryException e) {
			e.printStackTrace();
		}finally {
			try {
				db.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return primaryKey;
	}
}
