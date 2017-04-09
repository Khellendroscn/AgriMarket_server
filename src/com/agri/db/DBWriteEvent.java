package com.agri.db;

import java.util.Observable;

/**
 * Created by hyc on 2017/4/9.
 */
public class DBWriteEvent extends Observable{
    private Class cls;
    private Object key;
    DBWriteEvent(Class cls, Object key){
        this.cls = cls;
        this.key = key;
    }
    public Object getKey(){return key;}
    @Override
    public boolean equals(Object o){
        if(!(o instanceof DBWriteEvent)){
            return false;
        }else{
            DBWriteEvent event = (DBWriteEvent) o;
            return cls.equals(event.cls)&&key.equals(event.key);
        }
    }
    @Override
    public int hashCode(){
        return cls.hashCode()+7*key.hashCode();
    }

    public void setChanged(){
        super.setChanged();
    }
}
