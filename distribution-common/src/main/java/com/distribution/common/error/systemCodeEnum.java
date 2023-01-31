package com.distribution.common.error;

public enum  systemCodeEnum {
    SYSTEM_LOGIN_TIMEOUT(50001, "登录超时"),
    SYSTEM_NOAUTHER(50002, "需要先认证才能访问"),
    SYSTEM_403(50003, "403error"),
    SYSTEM_TOKEN_FAIL(50004, "Token非法")
    ;

    private int code;
    private  String msg;

    systemCodeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}

