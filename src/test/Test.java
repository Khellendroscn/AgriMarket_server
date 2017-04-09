package test;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by hyc on 2017/4/7.
 */
public class Test {
    public static void main(String[] args) {
        try {
            URL url = new URL("http://127.0.0.1:8080/config/DBConfig.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
            System.out.println(reader.readLine());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
