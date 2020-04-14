package com.confuture.myboot.controller;


import com.confuture.myboot.utils.JsonResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ValidationException;

@RestControllerAdvice
public class ExceptionHanldeController {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<JsonResult> exceptionHanlder(Exception ex){
        HttpStatus httpStatus;
        if (ex instanceof MethodArgumentNotValidException){
            httpStatus = HttpStatus.BAD_REQUEST;
        }
        else {
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(JsonResult.fail("fail", ex.getMessage()), httpStatus);
    }

}
