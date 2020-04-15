package com.confuture.myboot.controller;

import com.confuture.myboot.controller.req.UserRegister;
import com.confuture.myboot.dao.object.UserInfo;
import com.confuture.myboot.service.UserService;
import com.confuture.myboot.utils.JsonResult;
import com.confuture.myboot.utils.MyUtils;
import com.confuture.myboot.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.ModelAttributeMethodProcessor;

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
    public JsonResult userRegister(@RequestBody @Validated UserRegister userRegister){
        userService.createUser(userRegister);
        return JsonResult.ok();
    }

    @GetMapping("/otp")
    public JsonResult<Map<String, String>> getOtp(@RequestParam("phone") String phone){

        String laterOptRecordKey = MyUtils.getLaterRecordOptKey(phone);
        Object opt_record = redisUtil.get(laterOptRecordKey);
        if (opt_record == null){
            String randomOtp = MyUtils.generateOtp();
            redisUtil.setStringValue(laterOptRecordKey, randomOtp);
            redisUtil.expire(laterOptRecordKey, 30);
            Map<String, String> map = new HashMap<>();
            map.put(phone, randomOtp);
            return JsonResult.ok(map);
        }
        else {
            return JsonResult.fail("fail", "请不要频繁获取验证码");
        }
    }
}
