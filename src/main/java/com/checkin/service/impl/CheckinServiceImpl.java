package com.checkin.service.impl;

import com.checkin.common.Code;
import com.checkin.common.SessionConst;
import com.checkin.dao.CheckinMapper;
import com.checkin.dao.UserCheckinMapMapper;
import com.checkin.dao.UserMapper;
import com.checkin.entity.Checkin;
import com.checkin.entity.User;
import com.checkin.entity.UserCheckinMap;
import com.checkin.exception.InvalidException;
import com.checkin.exception.NoLoginException;
import com.checkin.service.CheckinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class CheckinServiceImpl implements CheckinService {

    @Autowired
    private CheckinMapper checkinDao;

    @Autowired
    private UserCheckinMapMapper userCheckinMapDao;

    @Autowired
    private UserMapper userDao;

    @Override
    public Checkin createCheckin(Checkin checkin, HttpSession session) {
        if (session.getAttribute(SessionConst.CURRENT_USER) == null) {
            throw new NoLoginException(Code.NO_LOGIN, "用户未登录");
        }
        if (checkin.getLocation() == null || checkin.getDetail() == null || checkin.getLimitTime() == null) {
            throw new InvalidException(Code.FAIL, "地址和详情不能为空");
        }
        Integer userId = (Integer) session.getAttribute(SessionConst.CURRENT_USER);
        checkin.setOrganizerId(userId);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        checkin.setCreateTime(timestamp);
        checkinDao.insertSelective(checkin);
        return checkinDao.findByTimestampAndOrgId(timestamp, userId);
    }

    @Override
    public List<Checkin> listAllCheckin() {
        return checkinDao.findAllCheckin();
    }

    public void joinCheckin(Integer id, HttpSession session) {
        Integer userId = (Integer) session.getAttribute(SessionConst.CURRENT_USER);
        if (userId == null) {
            throw new NoLoginException(Code.NO_LOGIN, "用户未登录");
        }
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Checkin checkin = checkinDao.selectByPrimaryKey(id);
        if ((Long) timestamp.getTime() - (Long) checkin.getCreateTime().getTime() > checkin.getLimitTime() * 60 * 1000) {
            throw new InvalidException(Code.FAIL, "签到已截止");
        }
        if (session.getAttribute(SessionConst.CURRENT_LOCATION) == null || !session.getAttribute(SessionConst.CURRENT_LOCATION).equals(checkin.getLocation())) {
            throw new InvalidException(Code.FAIL, "定位不对请重新设置");
        }
        UserCheckinMap userCheckinMap = new UserCheckinMap(id, userId, timestamp);
        if (userCheckinMapDao.findByidAnduserId(id, userId) != null) {
            throw new InvalidException(Code.FAIL, "你已签到");
        }
        userCheckinMapDao.insertSelective(userCheckinMap);
    }

    public List<String> listCheckinUser(Integer id) {
        List<UserCheckinMap> userCheckinMapList = userCheckinMapDao.findByCheckinId(id);
        List<String> userList = new ArrayList<String>();
        if (userCheckinMapList != null) {
            for (UserCheckinMap userCheckinMap : userCheckinMapList) {
                userList.add(userDao.selectByPrimaryKey(userCheckinMap.getUserId()).getUserName());
            }
        }
        return userList;
    }

    public List<Checkin> listUserCheckin(HttpSession session) {
        Integer userId = (Integer) session.getAttribute(SessionConst.CURRENT_USER);
        if (userId == null) {
            throw new NoLoginException(Code.NO_LOGIN, "用户未登录");
        }
        return checkinDao.findByOrgId(userId);
    }
}
