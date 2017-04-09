package com.myeclipseide.ws;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.agri.adapter.ShoppingCartItemAdapter;
import com.agri.bean.impl.FarmImpl;
import com.agri.bean.impl.ProductImpl;
import com.agri.db.DBPoolManager;
import net.khe.db2.DBQuaryException;
import net.khe.db2.DataBase;

@Produces({"application/xml" })
@Path("confirmorder")
public class ConfirmOrderResource {
	private DBPoolManager manager = DBPoolManager.getInstance();
	/*
	@POST
	@Produces({"application/xml" })
	public ShoppingCartItem getConfirmOrderItem(OrderItemRequest orderItemRequest) {
		ShoppingCartItem confirmOrderItem = new ShoppingCartItemImpl();
		try{
			Connection conn;
			Statement stmt;
			ResultSet res;
			Class.forName("com.mysql.jdbc.Driver").newInstance();				
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/agriculturalProductsDB","root","123456");
			stmt = conn.createStatement();	
			res = stmt.executeQuery("select product.productID,productName,productPrice,farmName,"+orderItemRequest.getProductCount()+" as productCount from agriculturalproductsdb.product,agriculturalproductsdb.farm where product.productID="+orderItemRequest.getProductID()+" and product.farmID = farm.farmID;");
			while(res.next()){
				int productID = res.getInt("productID");
				int productCount = res.getInt("productCount");
				String productName = res.getString("productName");
				float productPrice = res.getFloat("productPrice");
				String farmName = res.getString("farmName");
				

				confirmOrderItem.setProductID(productID);
				confirmOrderItem.setProductCount(productCount);
				confirmOrderItem.setProductName(productName);
				confirmOrderItem.setProductPrice(productPrice);
				confirmOrderItem.setFarmName(farmName);
				
			}
			return confirmOrderItem;
		}
		catch(Exception e){
			System.out.println(e);
		}
		return confirmOrderItem;
	}
	*/
	
	@POST
	@Produces({"application/xml" })
	public List<ShoppingCartItem> getConfirmOrderItems(List<OrderItemRequest> orderItemRequests) {
		List<ShoppingCartItem> confirmOrderItems = new ArrayList<ShoppingCartItem>();
		DataBase<FarmImpl> dbFarm = manager.newDataBase(FarmImpl.class);
		DataBase<ProductImpl> dbProduct = manager.newDataBase(ProductImpl.class);
		for(int i=0;i<orderItemRequests.size();i++)
		{
			OrderItemRequest request = orderItemRequests.get(i);
			try{
				ProductImpl product = dbProduct.getInstance(request.getProductID());
				FarmImpl farm = dbFarm.getInstance(product.getFarmId());
				ShoppingCartItem confirmItem = new ShoppingCartItemAdapter(product,farm);
				confirmItem.setProductCount(request.getProductCount());
				confirmOrderItems.add(confirmItem);
			}catch (DBQuaryException e){
				e.printStackTrace();
			} finally {
				try {
					dbFarm.close();
					dbProduct.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return confirmOrderItems;
	}
}
