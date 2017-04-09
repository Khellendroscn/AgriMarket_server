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
 * ���ݿ��Զ�ͬ��JavaBean������
 * �����Զ�ͬ�����ݿ�������д��ʱ�����б����JavaBean�������ֶ���д�����ݿ�Ķ�����ͬ������
 * ��������������JavaBean��
 * ������get�����᷵��һ����̬�������ͨ���ô��������Է������������JavaBean��
 * ����������ʱ����������״̬Ҳ����£������JavaBean����δ�ύ��set��������Щ�������ᶪʧ
 * ��������ͬ����ȡ���ܣ����Խ��������ת��ΪBeanLock���󣬵�����lock����
 * ��������Bean���ᱻ����Bean���£������ܹ���������Bean
 * ʹ��unlock���������Bean��������Ϊ����״̬
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
