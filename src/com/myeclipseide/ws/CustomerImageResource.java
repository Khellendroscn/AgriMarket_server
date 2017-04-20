package com.myeclipseide.ws;

import java.sql.*;

import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import com.agri.adapter.CustomerImageAdapter;
import com.agri.bean.impl.CustomerImpl;
import com.agri.db.DBPoolManager;
import net.khe.db2.DBQuaryException;
import net.khe.db2.DataBase;

@Singleton
@Produces({"application/xml" })
@Path("customerimage")
public class CustomerImageResource {
	private DBPoolManager manager = DBPoolManager.getInstance();
	@Path("{customerID}")
	@GET
	@Produces({"application/xml" })
	public CustomerImage getAddresses(@QueryParam("customerID") int customerID) {
		DataBase<CustomerImpl> db = manager.newDataBase(CustomerImpl.class);
		try {
			CustomerImpl customer = db.getInstance(customerID);
			return new CustomerImageAdapter(customer);
		} catch (DBQuaryException e) {
			e.printStackTrace();
		} finally {
			try {
				db.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}
