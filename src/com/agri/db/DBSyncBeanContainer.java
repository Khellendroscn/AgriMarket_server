package com.agri.db;

import com.agri.bean.Customer;
import com.agri.bean.impl.CustomerImpl;
import net.khe.db2.*;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by hyc on 2017/4/9.
 * 数据库自动同步JavaBean容器。
 * 当对自动同步数据库对象进行写入时，所有保存的JavaBean的主键字段与写入数据库的对象相同的容器
 * 都会更新自身保存的JavaBean。
 * 容器的get方法会返回一个动态代理对象，通过该代理对象可以访问容器保存的JavaBean。
 * 当容器更新时，代理对象的状态也会更新，如果对JavaBean做了未提交的set操作，这些操作都会丢失
 * 如果想禁用同步读取功能，可以将代理对象转化为BeanLock对象，调用其lock方法
 * 被锁定的Bean不会被其他Bean更新，但任能够更新其他Bean
 * 使用unlock解除锁定后，Bean会立即变为最新状态
 */
public class DBSyncBeanContainer<T> implements Observer {
    private T bean;
    private Class<T> iface;
    private DBPoolManager manager = DBPoolManager.getInstance();
    private boolean hasChanged = false;
    DBSyncBeanContainer(Class<T> iface, T bean){
        set(bean);
        this.iface = iface;
    }
    synchronized void set(T bean){
        this.bean = bean;
        this.hasChanged = true;
    }
    T get(){
        return (T)Proxy.newProxyInstance(
                iface.getClassLoader(),
                new Class[]{iface,WrapperedBean.class,BeanLock.class},
                new DBSyncProxyHandler()
        );
    }
    T getBean(){return bean;}
    @Override
    public void update(Observable o, Object arg) {
        DataBase<T> db = manager.newDataBase((Class<? extends T>) bean.getClass());
        try {
            T newBean = db.getInstance(arg);
            set(newBean);
        } catch (DBQuaryException e) {
            e.printStackTrace();
        }
    }
    class DBSyncProxyHandler implements InvocationHandler {
        WrapperedBean wrapper = ()->{return DBSyncBeanContainer.this;};
        boolean locked = false;
        T lockedBean = null;
        BeanLock lock = new BeanLock() {
            @Override
            public void lock() {
                locked = true;
                lockedBean = bean;
            }

            @Override
            public void unlock() {
                locked = false;
            }
        };
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if(method.getName().equals("__getWrapper__")){
                return wrapper.__getWrapper__();
            }else if(method.getName().equals("lock")){
                lock.lock();
                return null;
            }else if(method.getName().equals("unlock")){
                lock.unlock();
                return null;
            } else {
                if(locked){
                    return method.invoke(lockedBean,args);
                }else{
                    return method.invoke(bean, args);
                }
            }
        }
    }
    public static void main(String[] args) {
        DBPoolManager manager = DBPoolManager.getInstance();
        DataBase<Customer> db = manager.newAutoSyncDataBase(CustomerImpl.class);
        try {
            Customer[] customers = {
                    db.getInstance(1),
                    db.getInstanceBy("userName","testUser2"),
                    db.getInstance(2)
            } ;
            Arrays.stream(customers)
                    .map(customer -> customer.getUserName())
                    .forEach(System.out::println);
            customers[1].setUserName("testUser");
            DBSession<Customer> session = db.createSession();
            session.put(customers[1]);
            session.execute();
            Arrays.stream(customers)
                    .map(customer -> customer.getUserName())
                    .forEach(System.out::println);
        } catch (DBQuaryException e) {
            e.printStackTrace();
        } catch (DBWriteException e) {
            e.printStackTrace();
        }
    }
}
