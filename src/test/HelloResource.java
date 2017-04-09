package test;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import static javax.ws.rs.core.MediaType.TEXT_PLAIN;

/**
 * Created by hyc on 2017/4/7.
 */
@Path("/hello")
public class HelloResource {
    @GET
    @Produces(TEXT_PLAIN)
    public String hello(){
        return "Hello World!";
    }
    @GET
    @Produces(TEXT_PLAIN)
    @Path("echo/{str}")
    public String echo(@QueryParam("str")String str){
        return "echo: "+str;
    }
}
