package com.myeclipseide.ws;

import java.sql.*;
import java.util.*;
import java.util.Date;

import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import com.agri.adapter.ShoppingCartItemAdapter;
import com.agri.bean.impl.CustomerImpl;
import com.agri.bean.impl.FarmImpl;
import com.agri.bean.impl.ProductImpl;
import com.agri.bean.impl.ShoppingCartItemImpl;
import com.agri.db.DBPoolManager;
import net.khe.db2.DBQuaryException;
import net.khe.db2.DBSession;
import net.khe.db2.DBWriteException;
import net.khe.db2.DataBase;

@Singleton
@Produces({"application/xml" })
@Path("shoppingcart")
public class ShoppingCartResource {
	private DBPoolManager manager = DBPoolManager.getInstance();
	@GET
	@Path("{customerID}")
	@Produces({"application/xml" })
	public List<ShoppingCartItem> getShoppingCartItems(@QueryParam("customerID") int customerID) {
		List<ShoppingCartItem> shoppingCartItems = new ArrayList<ShoppingCartItem>();
		DataBase<CustomerImpl> dbCustomer = manager.newDataBase(CustomerImpl.class);
		DataBase<ProductImpl> dbProduct = manager.newDataBase(ProductImpl.class);
		DataBase<FarmImpl> dbFarm = manager.newDataBase(FarmImpl.class);
		try {
			CustomerImpl customer = dbCustomer.getInstance(customerID);
			List<ShoppingCartItemImpl> cart =
					customer.getShoppingCart();
			cart.stream().forEach(item -> {
				try {
					ProductImpl product = dbProduct.getInstance(item.getProductId());
					FarmImpl farm = dbFarm.getInstance(product.getFarmId());
					ShoppingCartItem item1 = new ShoppingCartItemAdapter(product,farm);
					item1.setProductCount(item.getAmount());
					shoppingCartItems.add(item1);
				} catch (DBQuaryException e) {
					e.printStackTrace();
				}
			});
		} catch (DBQuaryException e) {
			e.printStackTrace();
		}finally {
			try {
				dbCustomer.close();
				dbFarm.close();
				dbProduct.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return shoppingCartItems;
	}
	
	
	
	
	@POST
	@Path("add")
	@Produces("application/xml")
	@Consumes("text/xml")
	public void addShoppingCartItem(ShoppingCart shoppingCartItem) {
		DataBase<ShoppingCartItemImpl> db =
				manager.newDataBase(ShoppingCartItemImpl.class);
		ShoppingCartItemImpl item = null;
		try {
			item = (ShoppingCartItemImpl) db.select()
            .where("productId = "+shoppingCartItem.getProductID(),
					"customerId = "+shoppingCartItem.getCustomerID())
            .query().next();
		} catch (DBQuaryException e) {
			e.printStackTrace();
		}
		if(item == null){
			item = new ShoppingCartItemImpl();
			item.setProductId(shoppingCartItem.getProductID());
			item.setCreateTime(new Date());
			item.setCustomerId(shoppingCartItem.getCustomerID());
			item.setAmount(shoppingCartItem.getProductCount());
		}else{
			item.setAmount(item.getAmount()+shoppingCartItem.getProductCount());
		}
		DBSession<ShoppingCartItemImpl> session =
				db.createSession();
		session.put(item);
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
	
	
	
	@POST
	@Path("delete/query")
	@Produces("application/xml")
	@Consumes("text/xml")
	public void deleteShoppingCartItem(@QueryParam("productID") int productID,@QueryParam("customerID") int customerID) {
		System.out.println("shoppingcart del");
		DataBase<ShoppingCartItemImpl> db =
				manager.newDataBase(ShoppingCartItemImpl.class);
		DBSession<ShoppingCartItemImpl> session = db.createSession();
		try {
			db.select().where("productId = "+productID,"customerId = "+customerID)
                    .query().stream()
                    .forEach(o -> {
                        ShoppingCartItemImpl item =
                                (ShoppingCartItemImpl) o;
                        System.out.println(item.getId()+" "+int.class.isInstance(1));
                        try {
                            session.delete(item.getId());
                        } catch (DBWriteException e) {
                            e.printStackTrace();
                        }
                    });
			session.execute();
		} catch (DBQuaryException e) {
			e.printStackTrace();
		} catch (DBWriteException e) {
			e.printStackTrace();
		} finally {
			try {
				db.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	@POST
	@Path("basedoncoredata")
	@Produces({"application/xml" })
	public List<ShoppingCartItem> getShoppingCartItemsBasedOnCoreData(List<CoreDataShoppingCart> coreDataShoppingCarts) {
		List<ShoppingCartItem> shoppingCartItems = new ArrayList<ShoppingCartItem>();
		DataBase<ProductImpl> dbProduct = manager.newDataBase(ProductImpl.class);
		DataBase<FarmImpl> dbFarm = manager.newDataBase(FarmImpl.class);
		coreDataShoppingCarts.stream()
				.forEach(item->{
					try {
						ProductImpl product =
								dbProduct.getInstance(item.getProductID());
						FarmImpl farm =
								dbFarm.getInstance(product.getFarmId());
						shoppingCartItems.add(new ShoppingCartItemAdapter(product,farm));
					} catch (DBQuaryException e) {
						e.printStackTrace();
					}
				});
		try {
			dbFarm.close();
			dbProduct.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return shoppingCartItems;
	}
	

}
