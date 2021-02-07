package top.retain.xmsc.util;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * BASE64密码加密工具栏
 */
public class Base64Encoder {
    public static String encryptByBASE64(String password) throws NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        Base64.Encoder base64 =Base64.getEncoder();
        String result="";
        result=base64.encodeToString(md5.digest(password.getBytes()));
        return result;
    }
}
