package com.myeclipseide.ws;

import java.sql.*;
import java.util.Date;

import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.agri.bean.OrderEvaluate;
import com.agri.bean.impl.OrderEvaluateImpl;
import com.agri.db.DBPoolManager;
import net.khe.db2.DBSession;
import net.khe.db2.DBWriteException;
import net.khe.db2.DataBase;

@Singleton
@Produces({"application/xml" })
@Path("orderevaluateadd")
public class OrderEvaluateAddResource {
	private DBPoolManager manager = DBPoolManager.getInstance();
	@POST
	@Produces("application/xml")
	@Consumes("text/xml")
	public void addOrderEvaluate(OrderEvaluateAdd orderEvaluateAdd) {
		System.out.println(orderEvaluateAdd.getOrderID());
		OrderEvaluate evaluate = new OrderEvaluateImpl();
		evaluate.setContent(orderEvaluateAdd.getOrderEvaluateDescription());
		evaluate.setCreateTime(new Date());
		evaluate.setOrderId(orderEvaluateAdd.getOrderID());
		evaluate.setProductId(orderEvaluateAdd.getProductID());
		evaluate.setStar(orderEvaluateAdd.getStar());
		DataBase<OrderEvaluate> db = manager.newDataBase(OrderEvaluateImpl.class);
		DBSession<com.agri.bean.OrderEvaluate> session = db.createSession();
		session.put(evaluate);
		try {
			System.out.println(evaluate.getOrderId());
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
