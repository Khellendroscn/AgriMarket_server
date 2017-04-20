package webutil;

import sun.misc.BASE64Encoder;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by hyc on 2017/4/11.
 */
public class Encode {
    public static String byMd5(String passwd) throws NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        BASE64Encoder base64 = new BASE64Encoder();
        return base64.encode(md5.digest(passwd.getBytes()));
    }
}
