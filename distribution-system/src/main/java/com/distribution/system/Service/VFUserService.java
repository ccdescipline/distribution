package com.distribution.system.Service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.distribution.common.Pojo.vfUser;
import com.distribution.common.responsResult;
import com.distribution.common.Dto.vfUser.reqQueryUserDto;
import com.distribution.common.Dto.registerDto;
import com.distribution.common.Dto.vfUser.updateUserDto;

public interface VFUserService extends IService<vfUser> {

    /**
     * 用户登录
     * @param username
     * @param password
     * @return
     * @throws Exception
     */
    responsResult Login(String username,String password) throws Exception;

    /**
     * 退出登录
     * @param user
     * @return
     * @throws Exception
     */
    responsResult LoginOut(vfUser user) throws Exception;

    /**
     * 获取当前用户
     * @param user
     * @return
     */
    responsResult getcurrentinfo(vfUser user);

    /**
     * 注册
     * @return
     */
    responsResult register(registerDto registerDto);

    /**
     * 名字是否重复
     * @param username
     * @return
     */
    responsResult nameIsrepeat(String username);

    /**
     * 查询用户信息
     * @param userDto
     * @return
     */
    responsResult getUserlist(reqQueryUserDto userDto) ;


    /**
     * 重置密码
     * @param username
     * @return
     */
    responsResult resetpwd(String username);

    /**
     * 修改用户信息
     * @param updateUserDto
     * @return
     */
    responsResult updateinfo(updateUserDto updateUserDto);

}
