package com.agri.db;

import java.util.ArrayList;
import java.util.Collection;
import net.khe.db2.DataBase;

/**
 * Created by hyc on 2017/4/5.
 * �Զ�ͬ�������ݿ���б�
 * ����add����ʱ���¼���Ķ��󽫻��Զ�����/���µ����ݿ�
 */
public class DBSyncArrayList<E> extends ArrayList<E> {
    public DBSyncArrayList(Collection<E> collection){
        for(E elem:collection){
            super.add(elem);
        }
    }

    /**
     * ��Ԫ�ز��뵽���ݱ���List��
     * ����������ݱ����׳��쳣����Ԫ�ز��ᱻ����List��������false
     * @param elem Ҫ��ӵ�Ԫ��
     * @return true-����ɹ���false ����ʧ��
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
