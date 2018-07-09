package com.checkin.service.impl;

import com.checkin.dao.UserMapper;
import com.checkin.entity.User;
import com.checkin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userDao;

    @Override
    public boolean register(User user){
        String pwd = user.getUserPassword();
        userDao.insertSelective(user);
        return true;
    }



}
