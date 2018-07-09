package com.checkin.service;

import com.checkin.entity.User;

import javax.servlet.http.HttpSession;

public interface UserService {

    boolean register(User user);

    boolean login(String userName, String password, HttpSession session);

    boolean logout(HttpSession session);
}
