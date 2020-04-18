package com.confuture.myboot;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@SpringBootTest
class MybootApplicationTests {

    @Test
    void contextLoads() throws NoSuchAlgorithmException, UnsupportedEncodingException {
        String encryptString = "18811450152";
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        byte [] encryptContext = md5.digest(encryptString.getBytes("utf-8"));
        int i;
        StringBuffer buf = new StringBuffer("");
        for (int offset = 0; offset < encryptContext.length; offset ++){
            i = encryptContext[offset];
            if (i<0){
                i += 256;
            }
            if (i < 16){
                buf.append("0");
            }
            buf.append(Integer.toHexString(i));
        }
        System.out.println("~~~~~~~~~~~~md5:" + buf.toString());
    }
}
