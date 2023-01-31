package com.distribution.web.Security;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.distribution.common.Pojo.UserBean;
import com.distribution.common.Pojo.vfRole;
import com.distribution.common.Utils.JwtUtil;
import com.distribution.common.error.systemCodeEnum;
import com.distribution.common.mapper.vfRoleMapper;
import com.distribution.common.responsResult;
import com.distribution.common.Global.GlabalVars;
import io.jsonwebtoken.Claims;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * jwt过滤器
 */
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    private vfRoleMapper vfRoleMapper;

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @SneakyThrows
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        System.out.println(request.getRequestURL().toString());

        String token = request.getHeader("token");
        if (!StringUtils.hasText(token)) {
            filterChain.doFilter(request, response);
            //responseTokenError(response,"用户未认证");
            return;
        }
        String userid = null;
        //校验token
        try {
            Claims claims = JwtUtil.parseJWT(token);
            userid = claims.getSubject();
        } catch (Exception e) {
            e.printStackTrace();
            responseTokenError(response,systemCodeEnum.SYSTEM_TOKEN_FAIL);
            return;
        }
        //判断redis该用户是否超时存在
        if(Boolean.FALSE.equals(redisTemplate.boundHashOps(userid).hasKey(userid))){
            //throw new RuntimeException("用户登录超时请重新登录");
            responseTokenError(response,systemCodeEnum.SYSTEM_LOGIN_TIMEOUT);
            return;
        }

        //取出该用户相关信息 同时延长redis存储时间
        String redisUserObj = redisTemplate.boundHashOps(userid).get(userid).toString();
        redisTemplate.boundHashOps(userid).expire(GlabalVars.loginTimeout, TimeUnit.MILLISECONDS);
        UserBean userBean = JSON.parseObject(redisUserObj, UserBean.class);

        //查询角色信息
        vfRole roleinfo= vfRoleMapper.selectOne(Wrappers.<vfRole>lambdaQuery().eq(vfRole::getRoleid,userBean.getRoleid()));
        switch (roleinfo.getRoleName()){
            case "管理员":{

                userBean.setAuthorities(new ArrayList<>(Arrays.asList(new SimpleGrantedAuthority(GlabalVars.ROLE_ADMIN))));
                break;
            }
            case "经销商":{
                userBean.setAuthorities(new ArrayList<>(Arrays.asList(new SimpleGrantedAuthority(GlabalVars.ROLE_DELAER))));
                break;
            }
            case "供应商":{
                userBean.setAuthorities(new ArrayList<>(Arrays.asList(new SimpleGrantedAuthority(GlabalVars.ROLE_SUPPLIER))));
                break;
            }
        }

        //将用户信息，及权限交给security
        UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(userBean,null,userBean.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        filterChain.doFilter(request, response);
    }

    public static void responseTokenError(HttpServletResponse response, systemCodeEnum error) {
        HttpServletResponse httpServletResponse = response;
        httpServletResponse.setStatus(HttpStatus.OK.value());
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("application/json; charset=utf-8");
        try (PrintWriter out = httpServletResponse.getWriter()) {
            HashMap<String, Object> errorData = new HashMap<>();
            errorData.put("errorCode", error.getCode());
            errorData.put("errorMsg",error.getMsg());
            responsResult<HashMap<String, Object>> result = responsResult.error(errorData);
            String data = JSON.toJSONString(result);
            out.append(data);
        } catch (IOException e) {
            e.printStackTrace();

        }
    }
}
