package com.agri.bean.impl;

import net.khe.db2.annotations.*;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by hyc on 2017/4/3.
 */
@XmlRootElement
@DBTable({"customer_info"})
public class CustomerInfoImpl implements com.agri.bean.CustomerInfo {
    @Constraints(primaryKey = true, alloNull = false, autoIncrement = true)
    @Foreign(CustomerImpl.class)
    @SqlInt
    private int customerId;
    @SqlString(40)
    private String email;
    @SqlInt
    private int defaultAddressId;
    @SqlDate
    private Date createTime;
    @SqlDate
    private Date lastLoginTime;
    @SqlDate
    private Date currentLoginTime;
    @SqlInt
    private int loginCount;
    @SqlChars(1)
    private String status;
    @SqlString(100)
    private String imgPath;
    @Container(elementType = AddressImpl.class, containerType = ArrayList.class)
    private List<AddressImpl> addressList;

    @Override
    public int getCustomerId() {
        return customerId;
    }

    @Override
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public int getDefaultAddressId() {
        return defaultAddressId;
    }

    @Override
    public void setDefaultAddressId(int defaultAddressId) {
        this.defaultAddressId = defaultAddressId;
    }

    @Override
    public List<AddressImpl> getAddressList() {
        return addressList;
    }

    @Override
    public void setAddressList(List<AddressImpl> addressList) {
        this.addressList = addressList;
    }

    @Override
    public Date getCreateTime() {
        return createTime;
    }

    @Override
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    @Override
    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    @Override
    public Date getCurrentLoginTime() {
        return currentLoginTime;
    }

    @Override
    public void setCurrentLoginTime(Date currentLoginTime) {
        this.currentLoginTime = currentLoginTime;
    }

    @Override
    public int getLoginCount() {
        return loginCount;
    }

    @Override
    public void setLoginCount(int loginCount) {
        this.loginCount = loginCount;
    }

    @Override
    public String getStatus() {
        return status;
    }

    @Override
    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String getImgPath() {
        return imgPath;
    }

    @Override
    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }
}
