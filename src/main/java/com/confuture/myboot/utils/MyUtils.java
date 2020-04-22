package com.confuture.myboot.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class MyUtils {

    private static final String laterRecordOptKey = "LATER:OPT:RECORD:";
    private static final String dayOtpTime = "DAY:OPT:TIME:";
    private static final String loginToken = "LOGIN:USER:";

    private static final String encryptPasswordFactor = "chjawcodf=eweio434kcss12oqwzd";

    private static final int loginTokenlength = 50;

    public static String generateLoginToken(){
        String baseString = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        int randomBound = baseString.length() - 1;
        return getString(baseString, randomBound, loginTokenlength);
    }

    public static String generateOtp(){
        String baseString = "0123456789";
        int randomBound = baseString.length() - 1;
        int otpLength = 6;
        return getString(baseString, randomBound, otpLength);
    }

    private static String getString(String baseString, int randomBound, int otpLength) {
        Random random = new Random();
        StringBuffer randomOtp = new StringBuffer();
        for (int i=0;i<otpLength;i++){
            int randomPosition = random.nextInt(randomBound);
            randomOtp.append(baseString.charAt(randomPosition));
        }
        return randomOtp.toString();
    }

    public static String getLaterRecordOptKey(String phone){
        return laterRecordOptKey + phone;
    }

    public static String getLoginTokenKey(String phone){
        return loginToken + phone;
    }

    public static String getDayOptTimesKey(String phone){
        Date date = new Date();
        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
        String todayStr = sf.format(date);
        return dayOtpTime + todayStr + ":" + phone;
    }

    public static <T> T getFirstOrNull(List<T> list){
        return list.size() > 0 ? list.get(0) : null;
    }

    public static String generateEncryptedLoginPassword(String originalPassword) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        String encryptString = originalPassword + encryptPasswordFactor;
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
        return buf.toString();
    }

}
