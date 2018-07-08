package com.checkin.common;

/**
 * Created by bibi on 2018/7/7.
 */
public enum Code {
    SUCCESS(1001,"成功"),
    FAIL(1002,"失败"),
    NO_LOGIN(1003,"未登录");

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private int code;
    private String name;

    Code(int code, String name) {
        this.code = code;
        this.name = name;
    }

}
