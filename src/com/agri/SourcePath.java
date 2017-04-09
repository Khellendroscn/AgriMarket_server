package com.agri;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

/**
 * Created by hyc on 2017/4/6.
 * ∂¡»°SourcePath≈‰÷√Œƒº˛.
 */
public class SourcePath extends Properties{
    private static SourcePath instance;
    private SourcePath(){
        try {
            URL configURL = new URL("http://127.0.0.1/config/SourcePath.txt");
            load(configURL.openStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static SourcePath getInstance(){
        if(instance == null){
            instance = new SourcePath();
        }
        return instance;
    }
    @Override
    public String getProperty(String key){
        if(key.equals("host")){
            return "http://"+super.getProperty("host");
        }else if(key.equals("root")){
            return this.getProperty("host")+super.getProperty("root");
        }else{
            return this.getProperty("root")+super.getProperty(key);
        }
    }

    public static void main(String[] args) {
        SourcePath sourcePath = new SourcePath();
        System.out.println(sourcePath.getProperty("userHeadImg"));
    }
}
