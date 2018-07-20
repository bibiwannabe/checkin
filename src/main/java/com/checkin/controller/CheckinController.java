package com.checkin.controller;

import com.checkin.common.Code;
import com.checkin.common.Result;
import com.checkin.entity.Checkin;
import com.checkin.exception.InvalidException;
import com.checkin.exception.NoLoginException;
import com.checkin.service.CheckinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/checkin")
public class CheckinController {

    @Autowired
    private CheckinService checkinService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public Result<Checkin> createCheckin(String detail, String location, Integer limitTime, HttpSession session) {
        System.out.println(detail + location + limitTime);
        Checkin checkin = new Checkin(detail, location, limitTime);
        try {
            checkin = checkinService.createCheckin(checkin, session);
        } catch (InvalidException e) {
            return new Result.Builder<Checkin>(checkin).setCode(Code.FAIL).setMessage(e.getMessage()).build();
        } catch (NoLoginException e) {
            return new Result.Builder<Checkin>(checkin).setCode(Code.NO_LOGIN).setMessage(e.getMessage()).build();
        }
        return new Result.Builder<Checkin>(checkin).setCode(Code.SUCCESS).setMessage(Code.erroMessage.get(Code.SUCCESS)).build();
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public Result<List<Checkin>> listAllCheckin() {
        List<Checkin> checkinList = null;
        try {
            checkinList = checkinService.listAllCheckin();
        } catch (InvalidException e) {
            return new Result.Builder<List<Checkin>>(checkinList).setCode(Code.FAIL).setMessage(e.getMessage()).build();
        }
        return new Result.Builder<List<Checkin>>(checkinList).setCode(Code.SUCCESS).setMessage(Code.erroMessage.get(Code.SUCCESS)).build();
    }

    @RequestMapping(value = "/join", method = RequestMethod.POST)
    @ResponseBody
    public Result<String> joinCheckin(Integer id, HttpSession session) {
        try {
           checkinService.joinCheckin(id, session);
        } catch (InvalidException e) {
            return new Result.Builder<String>(e.getMessage()).setCode(Code.FAIL).setMessage(Code.erroMessage.get(Code.FAIL)).build();
        } catch (NoLoginException e){
            return new Result.Builder<String>(e.getMessage()).setCode(Code.NO_LOGIN).setMessage(Code.erroMessage.get(Code.NO_LOGIN)).build();
        }
        return new Result.Builder<String>("签到成功").setCode(Code.SUCCESS).setMessage(Code.erroMessage.get(Code.SUCCESS)).build();
    }


    @RequestMapping(value = "/{id}/list_users", method = RequestMethod.GET)
    @ResponseBody
    public Result<List<String>> listCheckinUser(@PathVariable(value = "id") Integer id){
        List<String> userList =  checkinService.listCheckinUser(id);
        return new Result.Builder<List<String>>(userList).setCode(Code.SUCCESS).setMessage(Code.erroMessage.get(Code.SUCCESS)).build();
    }


    @RequestMapping(value = "/my_checkin", method = RequestMethod.GET)
    @ResponseBody
    public Result<List<Checkin>> listUserCheckin(HttpSession session){
        List<Checkin> checkinList = null;
        try {
            checkinList =  checkinService.listUserCheckin(session);
        }catch (NoLoginException e){
            return new Result.Builder<List<Checkin>>(checkinList).setCode(Code.NO_LOGIN).setMessage(Code.erroMessage.get(Code.NO_LOGIN)).build();
        }
        return new Result.Builder<List<Checkin>>(checkinList).setCode(Code.SUCCESS).setMessage(Code.erroMessage.get(Code.SUCCESS)).build();
    }
}
