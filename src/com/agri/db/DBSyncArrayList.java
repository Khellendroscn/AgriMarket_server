package com.agri.db;

import java.util.ArrayList;
import java.util.Collection;
import net.khe.db2.DataBase;

/**
 * Created by hyc on 2017/4/5.
 * 自动同步到数据库的列表。
 * 调用add方法时，新加入的对象将会自动插入/更新到数据库
 */
public class DBSyncArrayList<E> extends ArrayList<E> {
    public DBSyncArrayList(Collection<E> collection){
        for(E elem:collection){
            super.add(elem);
        }
    }

    /**
     * 将元素插入到数据表与List。
     * 如果插入数据表是抛出异常，则元素不会被插入List，并返回false
     * @param elem 要添加的元素
     * @return true-插入成功；false 插入失败
     */
    @Override
    public boolean add(E elem){
        System.out.println("db-sync add");
        boolean flag = true;
        try{
            DBPoolManager manager = DBPoolManager.getInstance();
            DataBase db = manager.newDataBase(elem.getClass());
            db.put(elem);
        }catch (Exception e){
            flag = false;
            throw new RuntimeException(e);
        }
        if(flag){
            super.add(elem);
        }
        return flag;
    }
}
