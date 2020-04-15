package com.confuture.myboot.utils;

import java.util.Random;

public class MyUtils {

    private static final String laterRecordOptKey = "LATER:OPT:RECORD:";

    public static String generateRandomString(int length){
        String baseString = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        int randomBound = baseString.length() - 1;
        return getString(baseString, randomBound, length);
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

}
