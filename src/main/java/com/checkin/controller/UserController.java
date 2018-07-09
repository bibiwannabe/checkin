package com.checkin.controller;

import com.checkin.common.Code;
import com.checkin.common.Result;
import com.checkin.entity.User;
import com.checkin.exception.InvalidException;
import com.checkin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public Result<String> register(String username, String password){
        User user = new User(null,username, password);
        try {
            userService.register(user);
        }catch (InvalidException e){
            return new Result.Builder<String>(e.getMessage()).setCode(Code.FAIL).setMessage(Code.erroMessage.get(Code.FAIL)).build();
        }
        return new Result.Builder<String>("注册成功").setCode(Code.SUCCESS).setMessage(Code.erroMessage.get(Code.SUCCESS)).build();
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Result<String> login(String username, String password, HttpSession session){
        try {
            userService.login(username, password, session);
        }catch (InvalidException e){
            return new Result.Builder<String>(e.getMessage()).setCode(Code.FAIL).setMessage(Code.erroMessage.get(Code.FAIL)).build();
        }
        return new Result.Builder<String>("登录成功").setCode(Code.SUCCESS).setMessage(Code.erroMessage.get(Code.SUCCESS)).build();
    }

}
