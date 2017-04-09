package test;

import com.myeclipseide.ws.DefaultAddressResource;
import com.myeclipseide.ws.FarmOutline;
import com.myeclipseide.ws.FarmResource;
import com.myeclipseide.ws.NewOutlineResource;

import java.util.List;

/**
 * Created by hyc on 2017/4/7.
 */
public class TestResource {
    public static void main(String[] args) {
        DefaultAddressResource resource = new DefaultAddressResource();
        System.out.println(resource.getAddresses(1).getAddress());
    }
}
