package com.agri.bean;

import com.agri.bean.impl.AddressImpl;

import java.util.Date;
import java.util.List;

/**
 * Created by hyc on 2017/4/9.
 */
public interface CustomerInfo extends Bean {
    int getCustomerId();

    void setCustomerId(int customerId);

    String getEmail();

    void setEmail(String email);

    int getDefaultAddressId();

    void setDefaultAddressId(int defaultAddressId);

    List<AddressImpl> getAddressList();

    void setAddressList(List<AddressImpl> addressList);

    Date getCreateTime();

    void setCreateTime(Date createTime);

    Date getLastLoginTime();

    void setLastLoginTime(Date lastLoginTime);

    Date getCurrentLoginTime();

    void setCurrentLoginTime(Date currentLoginTime);

    int getLoginCount();

    void setLoginCount(int loginCount);

    String getStatus();

    void setStatus(String status);

    String getImgPath();

    void setImgPath(String imgPath);
}
