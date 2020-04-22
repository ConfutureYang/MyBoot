package com.confuture.myboot.service;

import com.confuture.myboot.controller.req.UserRegister;
import com.confuture.myboot.controller.resp.UserDetailInfo;
import com.confuture.myboot.dao.object.UserInfo;
import com.confuture.myboot.error.BusinessException;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public interface UserService {

    boolean createUser(UserRegister userRegister) throws BusinessException, UnsupportedEncodingException, NoSuchAlgorithmException;

    UserDetailInfo getUserDetail(String phone);

    String userLogin(String phone, String password) throws BusinessException, UnsupportedEncodingException, NoSuchAlgorithmException;
}
