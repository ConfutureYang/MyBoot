package com.confuture.myboot.error;

public enum EmBusinessError implements ErrorInterface {
    PARAMETER_ERROR(10001,"请求参数有问题"),
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
