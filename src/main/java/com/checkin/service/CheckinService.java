package com.checkin.service;

import com.checkin.entity.Checkin;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface CheckinService {
    Checkin createCheckin(Checkin checkin, HttpSession session);

    List<Checkin> listAllCheckin();
}
