package com.agri.resource;

import com.agri.bean.Customer;
import com.agri.bean.impl.CustomerImpl;
import com.agri.db.DBPoolManager;
import com.agri.model.State;
import net.khe.db2.DBQuaryException;
import net.khe.db2.DBWriteException;
import net.khe.db2.DataBase;

import javax.inject.Singleton;
import javax.ws.rs.*;
import java.sql.SQLException;

/**
 * Created by hyc on 2017/4/16.
 */
@Singleton
@Path("/customer")
@Produces({"application/json"})
public class CustomerResource {
    private DBPoolManager manager = DBPoolManager.getInstance();
    @GET
    @Path("/id/{id}")
    public CustomerImpl getCustomerById(@QueryParam("id") int id) {
        DataBase<CustomerImpl> db = manager.newDataBase(CustomerImpl.class);
        try {
            return db.getInstance(id);
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
    @GET
    @Path("/userName/{userName}")
    public CustomerImpl getCustomerByUserName(
            @QueryParam("userName") String userName) {
        DataBase<CustomerImpl> db = manager.newDataBase(CustomerImpl.class);
        try {
            return db.getInstanceBy("userName",userName);
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
    @POST
    public State put(CustomerImpl customer) {
        DataBase<CustomerImpl> db = manager.newDataBase(CustomerImpl.class);
        State ret = new State();
        try {
            db.put(customer);
            ret.setStatus(0);
        } catch (DBWriteException e) {
            ret.setStatus(-1);
            ret.setErrorMessage(e.getMessage());
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
