package com.agri.resource;

import com.agri.bean.impl.NewsImpl;
import com.agri.db.DBPoolManager;
import net.khe.db2.DBPool;
import net.khe.db2.DBQuaryException;
import net.khe.db2.DataBase;
import net.khe.util.CollectionData;
import net.khe.util.Generator;

import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by hyc on 2017/4/17.
 */
@Produces({"application/json"})
@Path("/news")
public class NewsResource {
    private DBPoolManager manager = DBPoolManager.getInstance();
    @GET
    @Path("{rowsPerPage}{pageIndex}")
    public List<NewsImpl> getNewsList(@QueryParam("rowsPerPage") int rowsPerPage,
                                      @QueryParam("pageIndex") int pageIndex) {
        DataBase<NewsImpl> db = manager.newDataBase(NewsImpl.class);
        try {
            return db.select().queryPage(pageIndex,rowsPerPage)
                    .stream()
                    .collect(Collectors.toList());
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
    @GET
    @Path("/pages/{rowsPerPage}")
    public int getPages(@QueryParam("rowsPerPage") int rowsPerPage) {
        DataBase<NewsImpl> db = manager.newDataBase(NewsImpl.class);
        try {
            int size = db.query().size();
            int pages = size/rowsPerPage;
            return size%rowsPerPage==0 ? pages : ++pages;
        } catch (DBQuaryException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
