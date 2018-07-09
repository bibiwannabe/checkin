package com.checkin.controller;

import com.checkin.common.Code;
import com.checkin.common.Result;
import com.checkin.entity.User;
import com.checkin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public Result<String> register(User user){
        try {
            userService.register(user);
        }catch (Exception e){
            return new Result.Builder<String>("用户名重复").setCode(Code.FAIL).setMessage(Code.erroMessage.get(Code.FAIL)).build();
        }
        return new Result.Builder<String>("注册成功").setCode(Code.SUCCESS).setMessage(Code.erroMessage.get(Code.SUCCESS)).build();
    }
}
