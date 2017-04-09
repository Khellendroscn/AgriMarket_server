package com.myeclipseide.ws;

import java.sql.*;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.agri.adapter.CustomerRequestAdapter;
import com.agri.bean.impl.CustomerImpl;
import com.agri.db.DBPoolManager;
import net.khe.db2.DBQuaryException;
import net.khe.db2.DataBase;

@Produces({"application/xml" })
@Path("customerconfirm")
public class CustomerRequestResource {
	private DBPoolManager manager = DBPoolManager.getInstance();
	@POST
	@Produces("application/xml")
	@Consumes("text/xml")
	public CustomerRequest getCustomerRequest(CustomerConfirm customerConfirm) {
		DataBase<CustomerImpl> db = manager.newDataBase(CustomerImpl.class);
		try {
			CustomerImpl customer = (CustomerImpl) db
					.select()
					.where("userName = '"+customerConfirm.getCustomerName()+"'")
                    .query()
					.next();
			return new CustomerRequestAdapter(customer);
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
