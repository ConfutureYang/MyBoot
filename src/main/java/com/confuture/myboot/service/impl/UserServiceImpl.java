package com.confuture.myboot.service.impl;

import com.confuture.myboot.controller.req.UserRegister;
import com.confuture.myboot.controller.resp.UserDetailInfo;
import com.confuture.myboot.dao.mapper.UserInfoMapper;
import com.confuture.myboot.dao.mapper.UserPasswordMapper;
import com.confuture.myboot.dao.object.UserInfo;
import com.confuture.myboot.dao.object.UserInfoExample;
import com.confuture.myboot.dao.object.UserPassword;
import com.confuture.myboot.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private UserPasswordMapper userPasswordMapper;

    @Override
    @Transactional
    public boolean createUser(UserRegister userRegister) {
        UserInfo userInfo = new UserInfo();
        BeanUtils.copyProperties(userRegister, userInfo);
        userInfoMapper.insertSelective(userInfo);

        UserPassword userPassword = new UserPassword();
        BeanUtils.copyProperties(userRegister, userPassword);
//        userPassword.setUserId(userInfo.getId());
        userPasswordMapper.insertSelective(userPassword);
        return true;
    }

    @Override
    public UserDetailInfo getUserDetail(String phone) {
        return null;
    }

}
