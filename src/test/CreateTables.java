package test;

import com.agri.bean.CustomerService;
import com.agri.bean.impl.AddressImpl;
import com.agri.bean.impl.CustomerServiceImpl;
import com.agri.db.DBPoolManager;
import net.khe.db2.DataBase;

import java.util.Date;

/**
 * Created by hyc on 2017/4/5.
 */
public class CreateTables {
    public static void main(String[] args) {
        DBPoolManager manager = DBPoolManager.getInstance();
        DataBase db = manager.newDataBase(CustomerServiceImpl.class);
        try{
            db.create();
            db.put(createTestObj());
            db.query().stream().forEach(System.out::println);
            db.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static Object createTestObj(){
        CustomerService service = new CustomerServiceImpl();
        service.setCreateTime(new Date());
        service.setEmail("test@test.com");
        service.setCustomerId(1);
        service.setQuestion("question");
        service.setTel("12345678901");
        return service;
    }
}
