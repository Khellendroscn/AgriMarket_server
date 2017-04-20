package com.agri.resource;

import com.agri.db.DBPoolManager;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import org.glassfish.jersey.server.ResourceConfig;
import test.HelloResource;
import test.TestResource;

/**
 * Created by hyc on 2017/4/7.
 */
public class ApiApplication extends ResourceConfig {
    public ApiApplication(){
        register(HelloResource.class);
        register(TestResource.class);
        packages("com.myeclipseide.ws");
        packages("com.agri.resource");

        register(JacksonJsonProvider.class);
    }
}
