package com.distribution.web.Config;

import com.alibaba.fastjson.JSONObject;
import com.distribution.common.error.systemCodeEnum;
import com.distribution.common.responsResult;
import com.distribution.web.Security.JwtAuthenticationTokenFilter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义403返回
 */
@Component
public class MyAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {
        JwtAuthenticationTokenFilter.responseTokenError(response, systemCodeEnum.SYSTEM_NOAUTHER);
    }

}