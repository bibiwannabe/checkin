package com.checkin.entity;

import java.util.Date;

public class UserCheckinMap {
    private Integer id;

    private Integer checkinId;

    private Integer userId;

    private Date lcreateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCheckinId() {
        return checkinId;
    }

    public void setCheckinId(Integer checkinId) {
        this.checkinId = checkinId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getLcreateTime() {
        return lcreateTime;
    }

    public void setLcreateTime(Date lcreateTime) {
        this.lcreateTime = lcreateTime;
    }
}