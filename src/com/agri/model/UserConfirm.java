package com.agri.model;

import com.agri.bean.Customer;
import net.khe.db2.annotations.Constraints;
import net.khe.db2.annotations.DBTable;
import net.khe.db2.annotations.SqlString;

/**
 * Created by hyc on 2017/4/17.
 */
@DBTable({"customer"})
public class UserConfirm {
    @SqlString(20)
    @Constraints(unique = true,alloNull = false)
    private String userName;
    @SqlString(32)
    @Constraints(alloNull = false)
    private String password;
    public UserConfirm(){}
    public UserConfirm(Customer customer) {
        setUserName(customer.getUserName());
        setPassword(customer.getPassword());
    }
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
