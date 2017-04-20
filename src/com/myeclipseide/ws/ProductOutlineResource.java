package com.myeclipseide.ws;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import com.agri.adapter.ProductOutlineAdapter;
import com.agri.bean.impl.ClassificationImpl;
import com.agri.bean.impl.FarmImpl;
import com.agri.bean.impl.ProductImpl;
import com.agri.db.DBPoolManager;
import net.khe.db2.DBQuaryException;
import net.khe.db2.DataBase;

@Singleton
@Produces({ "application/xml" })
@Path("productoutline")
public class ProductOutlineResource {
	private DBPoolManager manager = DBPoolManager.getInstance();
	@GET
	@Path("/query")
	@Produces({ "application/xml" })
	public List<ProductOutline> getProductOutlines(
			@QueryParam("fClassificationID") int fClassificationID,
			@QueryParam("orderkey") String orderkey) {
		List<ProductOutline> productOutlines = new ArrayList<ProductOutline>();
		DataBase<ClassificationImpl> dbClass = manager.newDataBase(ClassificationImpl.class);
		DataBase<ProductImpl> dbProduct = manager.newDataBase(ProductImpl.class);
		try {
			dbClass.select().where("fClassificationId = "+fClassificationID)
                    .query().stream()
                    .forEach(o -> {
                        ClassificationImpl classification =
                                (ClassificationImpl) o;
                        try {
                            dbProduct.select()
									.where("classificationId = "+classification.getId())
									.orderBy(orderkey)
                                    .query().stream()
                                    .forEach(o1 -> {
                                        ProductImpl product = (ProductImpl) o1;
                                        productOutlines.add(new ProductOutlineAdapter(product));
                                    });
                        } catch (DBQuaryException e) {
                            e.printStackTrace();
                        }
                    });
		} catch (DBQuaryException e) {
			e.printStackTrace();
		} finally {
			try {
				dbClass.close();
				dbProduct.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return productOutlines;
	}

	@GET
	@Path("search/query")
	@Produces({ "application/xml" })
	public List<ProductOutline> getProductOutlinesBySearch(
			@QueryParam("fClassificationID") int fClassificationID,
			@QueryParam("orderkey") String orderkey,
			@QueryParam("searchBarText") String searchBarText) {
		List<ProductOutline> productOutlines = new ArrayList<ProductOutline>();
		DataBase<ProductImpl> dbProduct = manager.newDataBase(ProductImpl.class);
		DataBase<ClassificationImpl> dbClass = manager.newDataBase(ClassificationImpl.class);
		try {
			dbClass.select().where("fClassificationId = "+fClassificationID)
                    .query().stream()
                    .forEach(o -> {
                        ClassificationImpl classification = (ClassificationImpl) o;
						try {
							dbProduct.select()
                                    .where("classificationId = "+classification.getId(),
                                            "name like '%"+searchBarText+"%'")
                                    .orderBy(orderkey)
                                    .query().stream()
                                    .forEach(o1->{
                                        ProductImpl product = (ProductImpl) o1;
                                        productOutlines.add(new ProductOutlineAdapter(product));
                                    });
						} catch (DBQuaryException e) {
							e.printStackTrace();
						}
					});
		} catch (DBQuaryException e) {
			e.printStackTrace();
		}finally {
			try {
				dbClass.close();
				dbProduct.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return productOutlines;
	}

	@GET
	@Path("farm/{farmID}")
	@Produces({ "application/xml" })
	public List<ProductOutline> getFarmProductOutlines(
			@QueryParam("farmID") int farmID) {
		List<ProductOutline> productOutlines = new ArrayList<ProductOutline>();
		DataBase<FarmImpl> db = manager.newDataBase(FarmImpl.class);
		try {
			FarmImpl farm = db.getInstance(farmID);
			List<ProductImpl> products = farm.getProductList();
			products.stream()
					.map(product -> new ProductOutlineAdapter(product))
					.forEach(productOutlines::add);
		} catch (DBQuaryException e) {
			e.printStackTrace();
		}
		return productOutlines;
	}

}
