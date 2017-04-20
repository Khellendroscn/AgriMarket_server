package com.myeclipseide.ws;

import java.sql.*;

import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.agri.bean.impl.CustomerServiceImpl;
import com.agri.db.DBPoolManager;
import net.khe.db2.DBSession;
import net.khe.db2.DBWriteException;
import net.khe.db2.DataBase;

@Singleton
@Produces({"application/xml" })
@Path("expertadvisor")
public class ExpertAdvisorResource {
	private DBPoolManager manager = DBPoolManager.getInstance();
	@POST
	@Path("add")
	@Produces("application/xml")
	@Consumes("text/xml")
	public void addAddressItem(ExpertAdvisor expertAdvisor) {
		CustomerServiceImpl cs = new CustomerServiceImpl();
		cs.setTel(expertAdvisor.getTelNumber());
		cs.setQuestion(expertAdvisor.getQuestion());
		cs.setEmail(expertAdvisor.getEmail());
		cs.setCustomerId(expertAdvisor.getCustomerID());
		cs.setCreateTime(new java.util.Date());
		DataBase<CustomerServiceImpl> db = manager.newDataBase(CustomerServiceImpl.class);
		DBSession<CustomerServiceImpl> session = db.createSession();
		session.put(cs);
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
