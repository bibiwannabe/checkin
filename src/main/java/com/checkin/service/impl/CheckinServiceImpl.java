package com.checkin.service.impl;

import com.checkin.common.Code;
import com.checkin.common.SessionConst;
import com.checkin.dao.CheckinMapper;
import com.checkin.entity.Checkin;
import com.checkin.exception.NoLoginException;
import com.checkin.service.CheckinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class CheckinServiceImpl implements CheckinService {

    @Autowired
    private CheckinMapper checkinDao;

    @Override
    public Checkin createCheckin(Checkin checkin, HttpSession session) {
        if (session.getAttribute(SessionConst.CURRENT_USER) == null) {
            throw new NoLoginException(Code.NO_LOGIN, "用户未登录");
        }
        if (checkin.getLocation() == null || checkin.getDetail() == null || checkin.getLimitTime() == null) {
            throw new NoLoginException(Code.FAIL, "地址和详情不能为空");
        }
        Integer userId = Integer.parseInt((String) session.getAttribute(SessionConst.CURRENT_USER));
        checkin.setOrganizerId(userId);
        checkinDao.insertSelective(checkin);
        return checkin;
    }

    public List<Checkin> listAllCheckin(){
        return checkinDao.findAllCheckin();
    }
}
