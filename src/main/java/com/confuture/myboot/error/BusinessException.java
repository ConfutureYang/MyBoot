package com.confuture.myboot.error;

public class BusinessException extends Exception implements ErrorInterface {


    private int errorCode;

    private String errorMsg;

    public BusinessException(ErrorInterface errorInterface){
        this.errorCode = errorInterface.getErrorCode();
        this.errorMsg = errorInterface.getErrorMsg();
    }

    @Override
    public int getErrorCode() {
        return this.errorCode;
    }

    @Override
    public String getErrorMsg() {
        return this.errorMsg;
    }
}
