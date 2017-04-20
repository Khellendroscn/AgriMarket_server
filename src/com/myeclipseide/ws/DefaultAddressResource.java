package com.myeclipseide.ws;

import java.sql.*;

import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import com.agri.adapter.AddressItemAdapter;
import com.agri.bean.impl.AddressImpl;
import com.agri.bean.impl.CustomerInfoImpl;
import com.agri.db.DBPoolManager;
import net.khe.db2.DBQuaryException;
import net.khe.db2.DBSession;
import net.khe.db2.DBWriteException;
import net.khe.db2.DataBase;

@Singleton
@Produces({"application/xml" })
@Path("defaultaddress")
public class DefaultAddressResource {
	private DBPoolManager manager = DBPoolManager.getInstance();
	@Path("{customerID}")
	@GET
	@Produces({"application/xml" })
	public AddressItem getAddresses(@QueryParam("customerID") int cCustomerID) {
		DataBase<CustomerInfoImpl> dbInfo = manager.newDataBase(CustomerInfoImpl.class);
		DataBase<AddressImpl> dbAddr = manager.newDataBase(AddressImpl.class);
		try {
			CustomerInfoImpl info = dbInfo.getInstance(cCustomerID);
			AddressImpl addr = dbAddr.getInstance(info.getDefaultAddressId());
			return new AddressItemAdapter(addr);
		} catch (DBQuaryException e) {
			e.printStackTrace();
		} finally {
			try {
				dbAddr.close();
				dbInfo.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	@POST
	@Path("edit")
	@Produces("application/xml")
	@Consumes("text/xml")
	public void editDefaultAddressAdd(DefaultAddressAdd defaultAddressAdd) {
		DataBase<CustomerInfoImpl> dbInfo = manager.newDataBase(CustomerInfoImpl.class);
		try {
			CustomerInfoImpl info =
					dbInfo.getInstance(defaultAddressAdd.getCustomerID());
			info.setDefaultAddressId(defaultAddressAdd.getAddressID());
			DBSession<CustomerInfoImpl> session = dbInfo.createSession();
			session.put(info);
			session.execute();
		} catch (DBQuaryException e) {
			e.printStackTrace();
		} catch (DBWriteException e) {
			e.printStackTrace();
		}finally {
			try {
				dbInfo.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
