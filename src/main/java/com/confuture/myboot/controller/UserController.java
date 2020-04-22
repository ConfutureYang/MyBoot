package com.confuture.myboot.controller;

import com.confuture.myboot.controller.req.UserRegister;
import com.confuture.myboot.error.BusinessException;
import com.confuture.myboot.error.EmBusinessError;
import com.confuture.myboot.service.UserService;
import com.confuture.myboot.utils.JsonResult;
import com.confuture.myboot.utils.MyUtils;
import com.confuture.myboot.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController("user")
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RedisUtil redisUtil;

    @PostMapping("/register")
    public JsonResult userRegister(@RequestBody @Validated UserRegister userRegister) throws BusinessException, UnsupportedEncodingException, NoSuchAlgorithmException {
        String laterOptRecordKey = MyUtils.getLaterRecordOptKey(userRegister.getPhone());
        String rightOpt = redisUtil.getStringValue(laterOptRecordKey);
        if (rightOpt != null && rightOpt.equals(userRegister.getUserOtp())){
            userService.createUser(userRegister);
        }
        else {
            throw new BusinessException(EmBusinessError.OTP_VALID_ERROR);
        }
        return JsonResult.ok();
    }

    @GetMapping("/otp")
    public JsonResult<Map<String, String>> getOtp(@RequestParam("phone") String phone) throws BusinessException {
        String laterOptRecordKey = MyUtils.getLaterRecordOptKey(phone);
        Object opt_record = redisUtil.get(laterOptRecordKey);
        if (opt_record != null){
            throw new BusinessException(EmBusinessError.GET_OTP_TOO_FREQUENT);
        }

        String dayOptTimeKey = MyUtils.getDayOptTimesKey(phone);
        String dayOptTimes = redisUtil.getStringValue(dayOptTimeKey);

        if (dayOptTimes != null && Integer.parseInt(dayOptTimes) >= 10 ){
            throw new BusinessException(EmBusinessError.OTP_DAY_TIMES_LIMIT_ERROR);
        }
        String randomOtp = MyUtils.generateOtp();
        redisUtil.setStringValue(laterOptRecordKey, randomOtp);
        redisUtil.expire(laterOptRecordKey, 30);
        Map<String, String> map = new HashMap<>();
        map.put(phone, randomOtp);
        redisUtil.incr(dayOptTimeKey, 1);
        redisUtil.expire(dayOptTimeKey, 3600 * 24);
        return JsonResult.ok(map);
    }

    @PostMapping("/login")
    public JsonResult userLogin(@RequestParam("phone") String phone, @RequestParam("password") String password) throws BusinessException, UnsupportedEncodingException, NoSuchAlgorithmException {
        String loginToken = userService.userLogin(phone, password);
        Map<String, String> map = new HashMap<>();
        map.put("token", loginToken);
        return JsonResult.ok(map);
    }



}
