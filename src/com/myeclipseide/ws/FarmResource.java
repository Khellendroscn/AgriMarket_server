package com.myeclipseide.ws;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import com.agri.adapter.FarmDetailAdatper;
import com.agri.adapter.FarmOutlineAdapter;
import com.agri.bean.impl.FarmImpl;
import com.agri.db.DBPoolManager;
import net.khe.db2.DBQuaryException;
import net.khe.db2.DataBase;

@Produces({"application/xml" })
@Path("farmoutline")
public class FarmResource {
	private DBPoolManager manager = DBPoolManager.getInstance();
	@GET
	@Produces({"application/xml" })
	public List<FarmOutline> getNewOutline() {
		List<FarmOutline> farmOutlines = new ArrayList<FarmOutline>();
		DataBase<FarmImpl> db = manager.newDataBase(FarmImpl.class);
		try {
			db.query().stream().forEach(farm -> {
                farmOutlines.add(new FarmOutlineAdapter(farm));
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
		return farmOutlines;
	}
	
	@GET
	@Produces({"application/xml" })
	@Path("farmdetail/{farmID}")
	public FarmDetail getNewDetail(@QueryParam("farmID") int farmID) {
		DataBase<FarmImpl> db = manager.newDataBase(FarmImpl.class);
		try {
			FarmImpl farm = db.getInstance(farmID);
			return new FarmDetailAdatper(farm);
		} catch (DBQuaryException e) {
			e.printStackTrace();
		}finally {
			try {
				db.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
}
