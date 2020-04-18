package com.confuture.myboot.service.impl;

import com.confuture.myboot.controller.req.UserRegister;
import com.confuture.myboot.controller.resp.UserDetailInfo;
import com.confuture.myboot.dao.mapper.UserInfoMapper;
import com.confuture.myboot.dao.mapper.UserPasswordMapper;
import com.confuture.myboot.dao.object.UserInfo;
import com.confuture.myboot.dao.object.UserInfoExample;
import com.confuture.myboot.dao.object.UserPassword;
import com.confuture.myboot.dao.object.UserPasswordExample;
import com.confuture.myboot.error.BusinessException;
import com.confuture.myboot.error.EmBusinessError;
import com.confuture.myboot.service.UserService;
import com.confuture.myboot.utils.MyUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private UserPasswordMapper userPasswordMapper;

    @Override
    @Transactional
    public boolean createUser(UserRegister userRegister) throws BusinessException, UnsupportedEncodingException, NoSuchAlgorithmException {

        UserInfoExample userInfoExample = new UserInfoExample();
        UserInfoExample.Criteria criteria = userInfoExample.createCriteria();
        criteria.andPhoneEqualTo(userRegister.getPhone());

        UserInfo exUserInfo = MyUtils.getFirstOrNull(userInfoMapper.selectByExample(userInfoExample));
        if (exUserInfo != null){
            throw new BusinessException(EmBusinessError.REGISTER_PHONE_EXIST_ERROR);
        }

        UserInfo userInfo = new UserInfo();
        BeanUtils.copyProperties(userRegister, userInfo);
        userInfoMapper.insertSelective(userInfo);

        UserPassword userPassword = new UserPassword();
        BeanUtils.copyProperties(userRegister, userPassword);
        userPassword.setUserId(userInfo.getId());
        String encryptPassword = MyUtils.generateEncryptedLoginPassword(userRegister.getPassword());
        userPassword.setPassword(encryptPassword);
        userPasswordMapper.insertSelective(userPassword);
        return true;
    }

    @Override
    public UserDetailInfo getUserDetail(String phone) {
        return null;
    }

    @Override
    public String userLogin(String phone, String password) throws BusinessException {
        UserInfoExample userInfoExample = new UserInfoExample();
        userInfoExample.createCriteria().andPhoneEqualTo(phone);
        UserInfo loginUserInfo = (UserInfo)MyUtils.getFirstOrNull(userInfoMapper.selectByExample(userInfoExample));
        if (loginUserInfo == null){
            throw new BusinessException(EmBusinessError.LOGIN_VALID_ERROR);
        }
        Long user_id = loginUserInfo.getId();

        UserPasswordExample userPasswordExample = new UserPasswordExample();
        UserPasswordExample.Criteria criteria = userPasswordExample.createCriteria();
//        criteria.and
    }

}
