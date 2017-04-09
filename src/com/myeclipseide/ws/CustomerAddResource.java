package com.myeclipseide.ws;

import java.sql.SQLException;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.agri.bean.impl.CustomerImpl;
import com.agri.bean.impl.CustomerInfoImpl;
import com.agri.db.DBPoolManager;
import net.khe.db2.DBSession;
import net.khe.db2.DBWriteException;
import net.khe.db2.DataBase;

@Produces({"application/xml" })
@Path("customeradd")
public class CustomerAddResource {
	private DBPoolManager manager = DBPoolManager.getInstance();
	@POST
	@Produces("application/xml")
	@Consumes("text/xml")
	public void addAddressItem(CustomerAdd customerAdd) {
		CustomerImpl customer = new CustomerImpl();
		customer.setUserName(customerAdd.getCustomerName());
		customer.setPassword(customerAdd.getPassword());
		customer.setStatus("0");
		CustomerInfoImpl info = new CustomerInfoImpl();
		info.setEmail(customerAdd.getEmail());
		customer.setInfo(info);
		DataBase<CustomerImpl> db = manager.newDataBase(CustomerImpl.class);
		DBSession<CustomerImpl> session = db.createSession();
		session.put(customer);
		try {
			session.execute();
		} catch (DBWriteException e) {
			e.printStackTrace();
		} finally {
			try {
				db.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
