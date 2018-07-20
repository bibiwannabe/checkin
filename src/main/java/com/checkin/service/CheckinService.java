package com.checkin.service;

import com.checkin.entity.Checkin;
import com.checkin.entity.User;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface CheckinService {
    Checkin createCheckin(Checkin checkin, HttpSession session);

    List<Checkin> listAllCheckin();

    void joinCheckin(Integer id, HttpSession session);

    List<String> listCheckinUser(Integer id);

    List<Checkin> listUserCheckin(HttpSession session);
}
