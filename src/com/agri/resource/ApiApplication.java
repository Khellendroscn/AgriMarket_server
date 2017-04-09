package com.agri.resource;

import com.agri.db.DBPoolManager;
import org.glassfish.jersey.server.ResourceConfig;
import test.HelloResource;

/**
 * Created by hyc on 2017/4/7.
 */
public class ApiApplication extends ResourceConfig {
    public ApiApplication(){
        register(HelloResource.class);
        packages("com.myeclipseide.ws");
    }
}
