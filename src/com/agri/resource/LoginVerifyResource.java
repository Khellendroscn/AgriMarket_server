package com.agri.resource;

import com.agri.bean.Customer;
import com.agri.bean.impl.CustomerImpl;
import com.agri.db.DBPoolManager;
import com.agri.model.State;
import com.agri.model.UserConfirm;
import net.khe.db2.DBQuaryException;
import net.khe.db2.DataBase;

import javax.inject.Singleton;
import javax.ws.rs.*;

/**
 * Created by hyc on 2017/4/13.
 */
@Singleton
@Produces({"application/json"})
@Path("/login_verify")
public class LoginVerifyResource {
    private DBPoolManager manager = DBPoolManager.getInstance();
    @POST
    public State verify(CustomerImpl customer){
        System.out.println("login_verify");
        System.out.println("userName: "+customer.getUserName());
        System.out.println("passwd: "+customer.getPassword());
        DataBase<UserConfirm> db = manager.newDataBase(UserConfirm.class);
        try {
            UserConfirm user = db.getInstanceBy("userName", customer.getUserName());
            if(user == null){
                return new State(-1,"用户名不存在");
            }else if(!user.getPassword().equals(customer.getPassword())){
                return new State(-2,"密码错误");
            }else{
                return new State(0,null);
            }
        } catch (DBQuaryException e) {
            e.printStackTrace();
        }
        return new State(-3,"UNKNOWN ERROR");
    }
}
