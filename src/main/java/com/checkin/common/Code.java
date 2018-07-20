package com.checkin.common;

import com.google.inject.internal.util.ImmutableMap;

import java.util.Map;

public class Code {
    public final static int SUCCESS = 10000;
    public final static int FAIL = 10001;
    public final static int NO_LOGIN = 10003;

    public final static Map<Integer,String> erroMessage = new ImmutableMap.Builder<Integer, String>()
            .put(SUCCESS, "成功")
            .put(FAIL, "失败")
            .put(NO_LOGIN, "未登录")
            .build();

}

