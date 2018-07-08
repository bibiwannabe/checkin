package com.checkin.common;

import java.io.Serializable;

/**
 * Created by bibi on 2018/7/7.
 */
public class Result implements Serializable{

    private int code;
    private String msg;
    private Object object;

    public Result builder<T>(Object object){

    }

}
