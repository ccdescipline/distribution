package com.distribution.web.Utils;

import com.distribution.common.Pojo.vfUser;
import org.springframework.security.core.context.SecurityContextHolder;

public class SessionUtils {

    /**
     * 获取当前用户信息
     * @return
     */
    public static vfUser GetCurrentUser(){
            return  (vfUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
