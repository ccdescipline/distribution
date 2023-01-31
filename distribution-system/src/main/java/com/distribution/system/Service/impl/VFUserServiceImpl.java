package com.distribution.system.Service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.UUID;
import com.alibaba.fastjson.JSON;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.distribution.common.Global.GlabalVars;
import com.distribution.common.Pojo.UserBean;
import com.distribution.common.Pojo.vfRole;
import com.distribution.common.Pojo.vfUser;
import com.distribution.common.Utils.JwtUtil;
import com.distribution.common.Utils.MD5Utils;
import com.distribution.common.mapper.vfRoleMapper;
import com.distribution.common.mapper.vfUserMapper;
import com.distribution.common.responsResult;
import com.distribution.common.Dto.vfUser.reqQueryUserDto;
import com.distribution.common.Dto.registerDto;
import com.distribution.common.Dto.vfUser.resQueryUserDto;
import com.distribution.common.Dto.vfUser.updateUserDto;
import com.distribution.system.Service.VFUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class VFUserServiceImpl extends ServiceImpl<vfUserMapper, vfUser> implements VFUserService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private vfUserMapper userMapper;

    @Autowired
    private vfRoleMapper vfRoleMapper;


    /**
     * 用户登录
     * @return
     */
    @Override
    public responsResult Login(String username, String password) throws Exception {

        vfUser user = getOne(Wrappers.<vfUser>lambdaQuery().eq(vfUser::getUsername, username));


        if(user==null){
            throw new Exception("用户不存在");
        }

        String target = MD5Utils.md5Encryption(password, user.getSalt());
        System.out.println(target);

        if(!target.equals(user.getUserpwd())){
            throw new Exception("密码错误");
        }

        //以hash方式存储redis中
        redisTemplate.boundHashOps(user.getUserid()).put(user.getUserid(), JSON.toJSONString(user));
        //设置redis过期时间
        redisTemplate.boundHashOps(user.getUserid()).expire(GlabalVars.loginTimeout, TimeUnit.MILLISECONDS);




        //更新登陆时间
        user.setLastlogintime(new Timestamp(System.currentTimeMillis()));
        updateById(user);



        //返回token结果
        HashMap<String,String> result  = new HashMap<>();
        result.put("token",JwtUtil.createJWT(user.getUserid()));
        return responsResult.success(result);
    }

    /**
     * 退出登录
     * @return
     */
    @Override
    public responsResult LoginOut(vfUser user) throws Exception {

        if(user == null){
            throw new Exception("没有登录");
        }
        //redis移除信息
        redisTemplate.delete(user.getUserid());

        return responsResult.success("success");
    }

    /**
     * 获取当前用户信息
     * @return
     */
    @Override
    public responsResult getcurrentinfo(vfUser user) {

        Map<String, Object> stringObjectMap = BeanUtil.beanToMap(user);

        //删除不要字段
        stringObjectMap.remove("salt");
        stringObjectMap.remove("userpwd");
        stringObjectMap.remove("createtime");
        stringObjectMap.remove("lastlogintime");
        stringObjectMap.remove("roleid");
        stringObjectMap.remove("userid");
        stringObjectMap.remove("userstatus");


        //添加信息
        stringObjectMap.put("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        stringObjectMap.put("name",user.getUsername());

        //查询角色信息
        vfRole roleinfo = vfRoleMapper.selectOne(Wrappers.<vfRole>lambdaQuery()
                .eq(vfRole::getRoleid,user.getRoleid())
        );

        if(roleinfo==null){
            responsResult.error("权限信息获取错误");
        }

        stringObjectMap.put("role",roleinfo.getRoleName());


        return responsResult.success(stringObjectMap);
    }


    @Override
    public responsResult register(registerDto registerDto) {
        vfUser user = new vfUser();

        String salt = UUID.randomUUID().toString();
        String password =  MD5Utils.md5Encryption(registerDto.getPassword(),salt);

        //固定信息
        user.setUserid(UUID.randomUUID().toString());
        user.setSalt(UUID.randomUUID().toString());
        user.setUserpwd(password);
        user.setUserstatus(1);
        user.setLastlogintime(new Timestamp(System.currentTimeMillis()));
        user.setCreatetime(LocalDateTime.now());
        user.setUpdatetime(LocalDateTime.now());
        user.setMoney(new BigDecimal(0));


        //用户填入信息
        user.setUsername(registerDto.getUsername());
        user.setCompanyname(registerDto.getCompanyname());
        user.setUsersex(registerDto.getSex() == 1?"男":"女");
        user.setUseremail(registerDto.getEmail());
        user.setUserphone(registerDto.getPhone());
        user.setRoleid(registerDto.getRoleid());

        //插入
        try{
            boolean res =  save(user);
            if(!res){
                throw new Exception();
            }
        }catch (DuplicateKeyException e){
            return  responsResult.error("用户名重复");
        }
        catch (Exception e){
            e.printStackTrace();
            return  responsResult.error("注册失败");
        }


        return responsResult.success("注册成功");
    }

    @Override
    public responsResult nameIsrepeat(String username) {
        vfUser user = getOne(Wrappers.<vfUser>lambdaQuery().eq(vfUser::getUsername,username));

        if(user==null){
            return responsResult.success(true);
        }else {
            return responsResult.success(false);
        }
    }

    @Override
    public responsResult getUserlist(reqQueryUserDto userDto)  {
        //Page page =  page(new Page<>(1,10));



//        List<vfUser> records = new LambdaQueryChainWrapper<vfUser>(userMapper)
//                .eq(vfUser::getUserStatus, 1)
//                .page(new Page<>(userDto.getIndex(), userDto.getPage())).getRecords();


       // LambdaQueryWrapper<vfUser> vfUserLambdaQueryWrapper = Wrappers.<vfUser>lambdaQuery();
        Page<Map<String, Object>> mapPage = userMapper.selectMapsPage(Page.of(userDto.getIndex(), userDto.getPage()),
                Wrappers.<vfUser>lambdaQuery()
                        .select(
                                vfUser::getUsername,
                                vfUser::getCompanyname,
                                vfUser::getUsersex,
                                vfUser::getUseremail,
                                vfUser::getUserphone,
                                vfUser::getUserstatus,
                                vfUser::getLastlogintime
                        )
                        .eq(vfUser::getUserstatus, userDto.getStatus())
                        .like(vfUser::getUsername,userDto.getUsername())
                        .like(vfUser::getCompanyname,userDto.getCompanyname())

        );

//        try{
//            Thread.sleep(1000);
//        }catch (Exception e) {
//
//        }


        return responsResult.success(resQueryUserDto.setdata(mapPage.getRecords(),mapPage.getPages()));
    }

    @Override
    public responsResult resetpwd(String username) {
        vfUser user = getOne(Wrappers.<vfUser>lambdaUpdate().eq(vfUser::getUsername, username));

        user.setUserpwd(MD5Utils.md5Encryption("123456",user.getSalt()));

        int res =  userMapper.updateById(user);

        if(res>0){
            return responsResult.success(GlabalVars.SUCCESSMSG);
        }else {
            return responsResult.error("重置失败");
        }
    }

    @Override
    public responsResult updateinfo(updateUserDto updateUserDto) {
        //先查询用户信息
        vfUser user = getOne(Wrappers.<vfUser>lambdaQuery().eq(vfUser::getUsername,updateUserDto.getUsername()));
        if(user==null){
            return responsResult.error("未找到用户");
        }

        user.setCompanyname(updateUserDto.getCompanyname());
        user.setUseremail(updateUserDto.getUseremail());
        user.setUserphone(updateUserDto.getUserphone());
        user.setUsersex(updateUserDto.getUsersex());
        user.setUserstatus(updateUserDto.getUserstatus());

        user.setUpdatetime(LocalDateTime.now());

        boolean b = updateById(user);
        if(b){
            return responsResult.success(GlabalVars.SUCCESSMSG);
        }else {
            return responsResult.error("更新失败");
        }
    }
}
