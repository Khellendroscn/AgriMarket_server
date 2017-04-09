package com.myeclipseide.ws;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import com.agri.adapter.NewDescriptionAdapter;
import com.agri.adapter.NewOutlineAdapter;
import com.agri.bean.impl.NewsImpl;
import com.agri.db.DBPoolManager;
import net.khe.db2.DBQuaryException;
import net.khe.db2.DataBase;

@Produces({"application/xml" })
@Path("newoutline")
public class NewOutlineResource {
	private DBPoolManager manager = DBPoolManager.getInstance();
	@GET
	@Produces({"application/xml" })
	public List<NewOutline> getNewOutline() {
		List<NewOutline> newOutlines = new ArrayList<NewOutline>();
		DataBase<NewsImpl> db = manager.newDataBase(NewsImpl.class);
		try {
			db.select().orderBy("content").query().stream()
                    .forEach(o -> {
                        NewsImpl news = (NewsImpl) o;
                        newOutlines.add(new NewOutlineAdapter(news));
                    });
		} catch (DBQuaryException e) {
			e.printStackTrace();
		}finally {
			try {
				db.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return newOutlines;
	}
	
	@GET
	@Produces({"application/xml" })
	@Path("newdetail/{newID}")
	public NewDescription getNewDetail(@QueryParam("newID") int newID) {
		DataBase<NewsImpl> db = manager.newDataBase(NewsImpl.class);
		NewDescription ret = new NewDescription();
		try {
			NewsImpl news = db.getInstance(newID);
			ret = new NewDescriptionAdapter(news);
		} catch (DBQuaryException e) {
			e.printStackTrace();
		}finally {
			try {
				db.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return ret;
	}
}
