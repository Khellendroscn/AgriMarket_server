package com.myeclipseide.ws;

import java.sql.*;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import com.agri.adapter.ProductDetailAdapter;
import com.agri.bean.impl.FarmImpl;
import com.agri.bean.impl.ProductImpl;
import com.agri.db.DBPoolManager;
import net.khe.db2.DBQuaryException;
import net.khe.db2.DataBase;

@Produces({"application/xml" })
@Path("productdetail")
public class ProductDetailResource {
	private DBPoolManager manager = DBPoolManager.getInstance();
	@GET
	@Path("{productID}")
	@Produces({"application/xml" }) 
	public ProductDetail getProductDetail(@QueryParam("productID") int cProductID) {
		ProductDetail productDetail = new ProductDetail();
		DataBase<ProductImpl> dbProduct = manager.newDataBase(ProductImpl.class);
		DataBase<FarmImpl> dbFarm = manager.newDataBase(FarmImpl.class);
		try {
			System.out.println(cProductID);
			ProductImpl product = dbProduct.getInstance(cProductID);
			System.out.println(product);
			FarmImpl farm = dbFarm.getInstance(product.getFarmId());
			productDetail = new ProductDetailAdapter(product,farm);
		} catch (DBQuaryException e) {
			e.printStackTrace();
		}finally {
			try {
				dbFarm.close();
				dbProduct.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return productDetail;
	}
	
}
