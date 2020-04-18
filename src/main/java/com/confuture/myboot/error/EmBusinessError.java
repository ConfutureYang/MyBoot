package com.confuture.myboot.error;

public enum EmBusinessError implements ErrorInterface {
    PARAMETER_ERROR(10001,"请求参数有问题"),

    //用户相关
    GET_OTP_TOO_FREQUENT(20001, "请不要频繁获取验证码"),
    OTP_DAY_TIMES_LIMIT_ERROR(20002, "一天获取验证码次数超限"),
    OTP_VALID_ERROR(20003, "验证码错误"),

    REGISTER_PHONE_EXIST_ERROR(21001, "手机号已经注册"),
    LOGIN_VALID_ERROR(21002, "手机号或者密码错误"),

    UNKNOWN_ERROR(50000,"未知错误"),
    ;

    private int errorCode;

    private String errorMsg;

    EmBusinessError(int errorCode, String errorMsg){
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    @Override
    public String getErrorMsg() {
        return this.errorMsg;
    }

    @Override
    public int getErrorCode() {
        return this.errorCode;
    }
}
