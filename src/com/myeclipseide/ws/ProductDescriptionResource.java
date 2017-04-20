package com.myeclipseide.ws;

import java.sql.*;

import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import com.agri.adapter.ProductDescriptionAdapter;
import com.agri.bean.impl.ProductImpl;
import com.agri.db.DBPoolManager;
import net.khe.db2.DBQuaryException;
import net.khe.db2.DataBase;

@Singleton
@Produces({"application/xml" })
@Path("productdescription")
public class ProductDescriptionResource {
	private DBPoolManager manager = DBPoolManager.getInstance();
	@GET
	@Path("{productID}")
	@Produces({"application/xml" })
	public ProductDescription getProductDescription(@QueryParam("productID") int cProductID) {
		ProductDescription productDescription = null;
		DataBase<ProductImpl> db = manager.newDataBase(ProductImpl.class);
		try {
			ProductImpl product = db.getInstance(cProductID);
			productDescription = new ProductDescriptionAdapter(product);
		} catch (DBQuaryException e) {
			e.printStackTrace();
		}finally {
			try {
				db.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return productDescription;
	}
	
}
