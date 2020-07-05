package com.zhaojm.study.login.uitls;

import com.zhaojm.study.login.common.SystemConst;
import org.springframework.util.Base64Utils;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 加密工具
 *
 * @author zhaojm
 * @date 2020/7/5 19:26
 */
public class EncryptUtil {  
    public static String getMD5(String inStr) {  
        MessageDigest md5;
        try {  
            md5 = MessageDigest.getInstance("MD5");
            byte[] md5Bytes = md5.digest(inStr.getBytes(StandardCharsets.UTF_8));
            return  Base64Utils.encodeToString(md5Bytes);
        } catch (Exception e) {
            return "";  
        } 
    }
    
    public static String toMd5(String inStr) {  
    	 byte[] secretByte;
         try {
             secretByte = MessageDigest.getInstance("md5").digest(inStr.getBytes());
         } catch (NoSuchAlgorithmException e) {
             throw new RuntimeException("找不到md5算法");
         }
         StringBuilder md5Code = new StringBuilder(new BigInteger(1, secretByte).toString(16));
         for (int i = 0; i < 32 - md5Code.length(); i++) {
             md5Code.insert(0, "0");
         }
         return md5Code.toString();
    }
}  