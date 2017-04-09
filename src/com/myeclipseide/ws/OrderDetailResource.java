package com.myeclipseide.ws;

import java.sql.*;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import com.agri.adapter.OrderDetailAdapter;
import com.agri.bean.Order;
import com.agri.bean.impl.AddressImpl;
import com.agri.bean.impl.FarmImpl;
import com.agri.bean.impl.OrderImpl;
import com.agri.db.DBPoolManager;
import net.khe.db2.DBQuaryException;
import net.khe.db2.DataBase;

@Produces({"application/xml" })
@Path("orderoutline/orderdetail")
public class OrderDetailResource {
	private DBPoolManager manager = DBPoolManager.getInstance();
	@Path("/query")
	@GET
	@Produces({"application/xml" })
	public OrderDetail getOrderDetail(@QueryParam("orderID") int cOrderID,@QueryParam("farmID") int cFarmID) {
		OrderDetail orderDetail = null;
		DataBase<Order> dbOrder = manager.newDataBase(OrderImpl.class);
		DataBase<AddressImpl> dbAddr = manager.newDataBase(AddressImpl.class);
		DataBase<FarmImpl> dbFarm = manager.newDataBase(FarmImpl.class);
		try {
			Order order = dbOrder.getInstance(cOrderID);
			AddressImpl addr = dbAddr.getInstance(order.getAddressId());
			FarmImpl farm = dbFarm.getInstance(cFarmID);
			orderDetail = new OrderDetailAdapter(order,farm,addr);
		} catch (DBQuaryException e) {
			e.printStackTrace();
		}finally {
			try {
				dbAddr.close();
				dbFarm.close();
				dbOrder.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return orderDetail;
	}
}
