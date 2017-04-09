package com.myeclipseide.ws;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.agri.adapter.FClassificationAdapter;
import com.agri.bean.impl.FClassificationImpl;
import com.agri.db.DBPoolManager;
import net.khe.db2.DBQuaryException;
import net.khe.db2.DataBase;

@Produces({"application/xml" })
@Path("fclassification")
public class FClassificationResource {
	private DBPoolManager manager = DBPoolManager.getInstance();
	@GET
	@Produces({"application/xml" })
	public List<FClassification> getFClassifications() {
		List<FClassification> fClassifications = new ArrayList<FClassification>();
		DataBase<FClassificationImpl> db =
				manager.newDataBase(FClassificationImpl.class);
		try {
			db.query().stream().forEach(fClassification -> {
                fClassifications.add(new FClassificationAdapter(fClassification));
            });
		} catch (DBQuaryException e) {
			e.printStackTrace();
		} finally {
			try {
				db.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return fClassifications;
	}
	
}



