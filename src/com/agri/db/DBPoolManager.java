package com.agri.db;

import net.khe.db2.*;
import net.khe.db2.annotations.KeyNotFoundException;
import net.khe.util.ClassVisitor;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URL;
import java.sql.SQLException;
import java.util.*;

/**
 * Created by hyc on 2017/4/3.
 * 数据库连接池管理器。
 * 通过该类的实例来获取连接池。
 * 该类为单例类，请使用DBPoolManager.getInstance();获取实例
 */
public class DBPoolManager {
    private DBPool pool;
    private DBPool autoSyncPool;
    private DBConfig config;
    private static volatile DBPoolManager instance;
    private Map<DBWriteEvent,DBWriteEvent> eventMap = new HashMap<>();
    private DBPoolManager() {
        try {
            URL configURL = new URL("http://127.0.0.1:8080/config/DBConfig.txt");
            config = new DBConfig(configURL.openStream());
            //config = new DBConfig("D:\\projects\\14系统源代码\\Web Service代码\\AgrProShoppingGuideRESTAPI\\web\\config\\DBConfig.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        pool = new DBPool();
        autoSyncPool = new AutoSyncDBPool();
    }
    public static DBPoolManager getInstance(){
        if(instance == null){
            synchronized (DBPoolManager.class){
                if(instance == null){
                    instance = new DBPoolManager();
                }
            }
        }
        return instance;
    }

    /**
     * 获取连接池
     * @return
     */
    public DBPool getPool(){return pool;}

    /**
     * 获取自动同步连接池
     * @return
     */
    public DBPool getAutoSyncPool(){return autoSyncPool;}
    /**
     * 获取DataBase实例并自动进行连接
     * @param cls 类型信息
     * @param <T> JavaBean类
     * @return
     */
    public <T>DataBase<T> newDataBase(Class<? extends T> cls){
        try {
            return pool.newDataBase(config,cls);
        } catch (KeyNotFoundException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取自动同步数据库
     * @param cls 类型信息
     * @param <T> 映射到数据表的类
     * @return 自动同步数据库
     */
    public <T>DataBase<T> newAutoSyncDataBase(Class<? extends T> cls){
        try {
            return autoSyncPool.newDataBase(config,cls);
        } catch (KeyNotFoundException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    static Object getKey(Class cls, Object obj){
        try{
            TableField tf = DataBase.lookUp(cls).getKey();
            Field keyField = cls.getDeclaredField(tf.getNameInClass());
            ClassVisitor visitor = new ClassVisitor(cls);
            return visitor.getGetter(keyField).invoke(obj);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    class AutoSyncDBPool extends DBPool{
        @Override
        protected <T> DataBase<T> createDB(DBConfig config, Class<? extends T> cls) throws KeyNotFoundException, ClassNotFoundException {
            return new AutoSyncDB<T>(config,cls);
        }
        class AutoSyncDB<T> extends DataBaseInPool<T>{
            public AutoSyncDB(DBConfig config, Class<? extends T> cls) throws ClassNotFoundException, KeyNotFoundException {
                super(config,cls);
            }
            private Class getInterface(Class cls){
                String clsName = cls.getName();
                String ifaceName = clsName.replace("Impl","");
                ifaceName = ifaceName.replace(".impl","");
                try {
                    return Class.forName(ifaceName);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                return null;
            }
            @Override
            public T getInstance(Object primaryKey) throws DBQuaryException {
                T obj = super.getInstance(primaryKey);
                if(obj != null){
                    DBSyncBeanContainer<T> container =
                            new DBSyncBeanContainer<T>(getInterface(cls),obj);
                    DBWriteEvent event = new DBWriteEvent(cls,primaryKey);
                    if(!eventMap.containsKey(event)){
                        eventMap.put(event,event);
                    }
                    eventMap.get(event).addObserver(container);
                    return container.get();
                }
                return null;
            }

            @Override
            public T getInstanceBy(String keyName, Object key) throws DBQuaryException {
                T obj = super.getInstanceBy(keyName,key);
                if(obj != null){
                    DBSyncBeanContainer<T> container =
                            new DBSyncBeanContainer<T>(getInterface(cls),obj);
                    Object primaryKey = getKey(cls,obj);
                    DBWriteEvent event = new DBWriteEvent(cls,primaryKey);
                    if(!eventMap.containsKey(event)){
                        eventMap.put(event,event);
                    }
                    eventMap.get(event).addObserver(container);
                    return container.get();
                }
                return null;
            }
            @Override
            public DBSession<T> createSession(){
                return new AutoSycnSession<T>(this,cls);
            }
            @Override
            public void put(Object obj) throws DBWriteException {
                WrapperedBean wrapper = (WrapperedBean) obj;
                T bean = (T) wrapper.__getWrapper__().getBean();
                super.put(bean);
                DBWriteEvent event = new DBWriteEvent(cls,getKey(cls,bean));
                event.notifyObservers(event.getKey());
            }
        }
        class AutoSycnSession<T> extends DBSession<T>{
            private List<DBWriteEvent> events = new LinkedList<>();
            AutoSycnSession(DataBase<T> db, Class<? extends T> cls) {
                super(db, cls);
            }
            @Override
            public void put(Object obj){
                WrapperedBean wrapper = (WrapperedBean) obj;
                T bean = (T)wrapper.__getWrapper__().getBean();
                super.put(bean);
                DBWriteEvent event =
                        new DBWriteEvent(cls,getKey(cls,bean));
                events.add(event);
            }
            @Override
            public void execute() throws DBWriteException {
                super.execute();
                for(DBWriteEvent event:events){
                    DBWriteEvent temp = eventMap.get(event);
                    temp.setChanged();
                    temp.notifyObservers(event.getKey());
                }
            }
        }
    }
}
