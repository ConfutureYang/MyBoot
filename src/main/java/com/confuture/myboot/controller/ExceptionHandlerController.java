package com.confuture.myboot.controller;

import com.confuture.myboot.error.EmBusinessError;
import com.confuture.myboot.utils.JsonResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.*;

@RestControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<JsonResult> exceptionHandler(Exception ex){
        HttpStatus httpStatus;
        Map<Object, Object> errorData = new HashMap<>();
        if (ex instanceof MethodArgumentNotValidException){
            return handleParameterInvalid(ex);
        }
        else if (ex instanceof BindException){
            return handleParameterInvalid(ex);
        }
        else {
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            errorData.put("errorCode", EmBusinessError.UNKNOWN_ERROR.getErrorCode());
            errorData.put("errorMsg", EmBusinessError.UNKNOWN_ERROR.getErrorMsg());
        }
        return new ResponseEntity<>(JsonResult.fail("fail", errorData), httpStatus);
    }

    private ResponseEntity<JsonResult> handleParameterInvalid(Exception ex){
        BindingResult bindResult;

        if (ex instanceof BindException){
            BindException bindException = (BindException) ex;
            bindResult = bindException.getBindingResult();
        }
        else{
            MethodArgumentNotValidException methodArgumentNotValidException = (MethodArgumentNotValidException) ex;
            bindResult = methodArgumentNotValidException.getBindingResult();
        }

        List<String> errorMsgList = new ArrayList<>();
        if (bindResult.hasErrors()){
            List<FieldError> fieldErrorList = bindResult.getFieldErrors();
            fieldErrorList.forEach(error -> {
                errorMsgList.add(error.getDefaultMessage());
            });
        }

        Map<Object, Object> errorData = new HashMap<>();
        errorData.put("errorCode", EmBusinessError.PARAMETER_ERROR.getErrorCode());
        errorData.put("errorMsg", String.join(",", errorMsgList));
        return new ResponseEntity<>(JsonResult.fail("fail", errorData), HttpStatus.BAD_REQUEST);
    }
}
