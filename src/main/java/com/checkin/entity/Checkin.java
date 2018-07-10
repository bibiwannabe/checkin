package com.checkin.entity;

import java.util.Date;

public class Checkin {
    private Integer id;

    private Integer organizerId;

    private String detail;

    private String location;

    private Date createTime;

    private Integer limitTime;

    public Checkin(String detail, String location, Integer limitTime) {
        this.detail = detail;
        this.location = location;
        this.limitTime = limitTime;
    }

    public Checkin() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrganizerId() {
        return organizerId;
    }

    public void setOrganizerId(Integer organizerId) {
        this.organizerId = organizerId;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail == null ? null : detail.trim();
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location == null ? null : location.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getLimitTime() {
        return limitTime;
    }

    public void setLimitTime(Integer limitTime) {
        this.limitTime = limitTime;
    }
}