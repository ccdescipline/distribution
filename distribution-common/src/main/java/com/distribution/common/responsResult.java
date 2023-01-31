package com.distribution.common;

import com.distribution.common.error.errorMsg;
import lombok.Data;

@Data
public class responsResult<T> {
    /** 200:操作成功  -1：操作失败**/

    // http 状态码
    private boolean success;

    // 返回的数据
    private T data;

    public static <T> responsResult<T> success(T data) {
        responsResult<T> responseBean = new responsResult<>();
        responseBean.setSuccess(true);
        responseBean.setData(data);
        return responseBean;
    }


    public static <T> responsResult<T> error(T errorData) {
        responsResult<T> responseBean = new responsResult<>();
        responseBean.setSuccess(false);
        errorMsg errorMsg = new errorMsg(errorData);
        responseBean.setData((T) errorMsg);
        return responseBean;
    }

    public static <T> responsResult<T> success() {
        responsResult<T> responseBean = new responsResult<>();
        responseBean.setSuccess(true);
        return responseBean;
    }
}

