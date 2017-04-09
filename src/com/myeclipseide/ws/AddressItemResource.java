package com.myeclipseide.ws;

import com.agri.adapter.AddressItemAdapter;
import com.agri.bean.impl.AddressImpl;
import com.agri.bean.impl.CustomerImpl;
import com.agri.bean.impl.CustomerInfoImpl;
import com.agri.db.DBPoolManager;
import net.khe.db2.DBQuaryException;
import net.khe.db2.DBSession;
import net.khe.db2.DBWriteException;
import net.khe.db2.DataBase;

import javax.ws.rs.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Produces({"application/xml" })
@Path("address")
public class AddressItemResource {
	private DBPoolManager manager = DBPoolManager.getInstance();
	@Path("{customerID}")
	@GET
	@Produces({"application/xml" })
	public List<AddressItem> getAddresses(@QueryParam("customerID") int customerID) {
		DataBase<AddressImpl> addrDB = manager.newDataBase(AddressImpl.class);
		try {
			return (List<AddressItem>) addrDB.select()
					.where("customerId = "+customerID)
					.query()
					.stream()
					.map(o -> new AddressItemAdapter((AddressImpl)o))
					.collect(Collectors.toList());
		} catch (DBQuaryException e) {
			e.printStackTrace();
		}finally {
			try {
				addrDB.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return new ArrayList<>();
	}


	@Path("defaultaddress/{customerID}")
	@GET
	@Produces({"application/xml" })
	public AddressItem getDefaultAddress(@QueryParam("customerID") int customerID) {
		DataBase<CustomerImpl> customerDB = manager.newDataBase(CustomerImpl.class);
		DataBase<AddressImpl> addrDB = manager.newDataBase(AddressImpl.class);
		try {
			CustomerImpl customer = customerDB.getInstance(customerID);
			int addrId = customer.getInfo().getDefaultAddressId();
			AddressImpl address = addrDB.getInstance(addrId);
			return new AddressItemAdapter(address);
		} catch (DBQuaryException e) {
			e.printStackTrace();
		}finally {
			try {
				customerDB.close();
				addrDB.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return new AddressItem();
	}



	@POST
	@Path("edit")
	@Produces("application/xml")
	@Consumes("text/xml")
	public void editAddressItem(AddressItem addressItem) {
		DataBase<AddressImpl> addrDB = manager.newDataBase(AddressImpl.class);
		try {
			AddressImpl addr = addrDB.getInstance(addressItem.getAddressID());
			addr.setContent(addressItem.getAddress());
			addr.setTel(addressItem.getTelNumber());
			addr.setName(addressItem.getName());
			DBSession<AddressImpl> session = addrDB.createSession();
			session.put(addr);
			session.execute();
		} catch (DBQuaryException e) {
			e.printStackTrace();
		} catch (DBWriteException e) {
			e.printStackTrace();
		}finally {
			try {
				addrDB.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}


	@POST
	@Path("add")
	@Produces("application/xml")
	@Consumes("text/xml")
	public void addAddressItem(AddressItem addressItem) {
		DataBase<AddressImpl> addrDB = manager.newDataBase(AddressImpl.class);
		DataBase<CustomerInfoImpl> infoDB = manager.newDataBase(CustomerInfoImpl.class);
		AddressImpl addr = new AddressImpl();
		addr.setContent(addressItem.getAddress());
		addr.setTel(addressItem.getTelNumber());
		addr.setCustomerId(addressItem.getCustomerID());
		addr.setName(addressItem.getName());
		try {
			DBSession<AddressImpl> session = addrDB.createSession();
			session.put(addr);
			session.execute();
			CustomerInfoImpl info = infoDB.getInstance(addr.getCustomerId());
			int id = addrDB.query().stream()
					.map(address -> address.getId())
					.max(Integer::compareTo).get();
			if(info.getDefaultAddressId() < 1){
				info.setDefaultAddressId(id);
				DBSession<CustomerInfoImpl> session1 = infoDB.createSession();
				session1.put(info);
				session1.execute();
			}
		} catch (DBWriteException e) {
			e.printStackTrace();
		} catch (DBQuaryException e) {
			e.printStackTrace();
		}
	}


	@POST
	@Path("delete")
	@Produces("application/xml")
	@Consumes("text/xml")
	public void deleteAddressItem(AddressItem addressItem) {
		DataBase<AddressImpl> addrDB = manager.newDataBase(AddressImpl.class);
		DBSession<AddressImpl> session = addrDB.createSession();
		try {
			session.delete(addressItem.getAddressID());
			session.execute();
		} catch (DBWriteException e) {
			e.printStackTrace();
		} finally {
			try {
				addrDB.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

}
