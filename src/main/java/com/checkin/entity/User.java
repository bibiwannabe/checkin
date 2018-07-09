package com.checkin.entity;

public class User {
    private Integer userId;

    private String userName;

    private String userPassword;

    public User(Integer userId, String username, String password) {
        this.userId = userId;
        this.userName = username;
        this.userPassword = password;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword == null ? null : userPassword.trim();
    }
}