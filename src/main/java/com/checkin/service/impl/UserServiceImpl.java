package com.checkin.service.impl;

import com.checkin.common.Code;
import com.checkin.common.SessionConst;
import com.checkin.dao.UserMapper;
import com.checkin.entity.User;
import com.checkin.exception.InvalidException;
import com.checkin.service.UserService;
import com.checkin.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userDao;

    @Override
    public boolean register(User user) {
        if (user.getUserName() == null || user.getUserPassword() == null || user.getUserPassword().length() < 6) {
            throw new InvalidException(Code.FAIL, "用户名，用户密码不能为空，用户密码长度不能少于6");
        }
        User oldUser = userDao.findUserByUserName(user.getUserName());
        if (oldUser != null) {
            throw new InvalidException(Code.FAIL, "用户名已存在");
        }
        String encryptPassword = MD5Util.MD5EncodeUtf8(user.getUserPassword());
        user.setUserPassword(encryptPassword);
        userDao.insertSelective(user);
        return true;
    }

    @Override
    public boolean login(String userName, String password, HttpSession session) {
        String pwd = MD5Util.MD5EncodeUtf8(password);
        User user = userDao.selectByNameAndPassword(userName, pwd);
        if (user == null) {
            throw new InvalidException(Code.FAIL, "用户名或密码不正确");
        }
        session.setAttribute(SessionConst.CURRENT_USER, user.getUserId());
        return true;
    }

    @Override
    public boolean logout(HttpSession session) {
        if (session.getAttribute(SessionConst.CURRENT_USER) == null) {
            throw new InvalidException(Code.NO_LOGIN, "你似乎未曾登录");
        }
        session.removeAttribute(SessionConst.CURRENT_USER);
        return true;
    }

    public void setLocation(String location, HttpSession session){
        if (session.getAttribute(SessionConst.CURRENT_USER) == null) {
            throw new InvalidException(Code.NO_LOGIN, "你似乎未曾登录");
        }
        session.setAttribute(SessionConst.CURRENT_LOCATION, location);
    }
}
