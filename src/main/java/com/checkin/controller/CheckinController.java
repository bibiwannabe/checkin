package com.checkin.controller;

import com.checkin.common.Code;
import com.checkin.common.Result;
import com.checkin.entity.Checkin;
import com.checkin.exception.InvalidException;
import com.checkin.service.CheckinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/checkin")
public class CheckinController {

    @Autowired
    private CheckinService checkinService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    Result<Checkin> createCheckin(Checkin checkin, HttpSession session){
        Checkin newCheckin;
        try {
            newCheckin = checkinService.createCheckin(checkin, session);
        }catch (InvalidException e){
            return new Result.Builder<Checkin>(checkin).setCode(Code.FAIL).setMessage(e.getMessage()).build();
        }
        return new Result.Builder<Checkin>(newCheckin).setCode(Code.SUCCESS).setMessage(Code.erroMessage.get(Code.SUCCESS)).build();
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    Result<List<Checkin>> listAllCheckin(){
        List<Checkin> checkinList = null;
        try {
            checkinList = checkinService.listAllCheckin();
        }catch (InvalidException e){
            return new Result.Builder<List<Checkin>>(checkinList).setCode(Code.FAIL).setMessage(e.getMessage()).build();
        }
        return new Result.Builder<List<Checkin>>(checkinList).setCode(Code.SUCCESS).setMessage(Code.erroMessage.get(Code.SUCCESS)).build();
    }

}
