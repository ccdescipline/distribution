package com.distribution.common.error;

import lombok.Data;

@Data
public class errorMsg<T> {
    private T errorMsg;
    private int errorCode;

    public errorMsg(T errorMsg, int errorCode) {
        this.errorMsg = errorMsg;
        this.errorCode = errorCode;
    }

    public errorMsg(T errorMsg) {
        this.errorMsg = errorMsg;
        this.errorCode = 50000;
    }

}
