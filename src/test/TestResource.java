package test;

import com.myeclipseide.ws.DefaultAddressResource;
import com.myeclipseide.ws.FarmOutline;
import com.myeclipseide.ws.FarmResource;
import com.myeclipseide.ws.NewOutlineResource;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by hyc on 2017/4/7.
 */
@Path("/test")
@Produces({"application/json"})
public class TestResource {
    @GET
    public TestBean testGet() {
        return new TestBean();
    }
    @POST
    public TestBean testPost(TestBean bean) {
        bean.setMessage("received!");
        return bean;
    }
}
