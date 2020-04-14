package com.confuture.myboot.service;

import com.confuture.myboot.controller.req.UserRegister;
import com.confuture.myboot.controller.resp.UserDetailInfo;
import com.confuture.myboot.dao.object.UserInfo;

import java.util.List;

public interface UserService {

    boolean createUser(UserRegister userRegister);

    UserDetailInfo getUserDetail(String phone);

}
