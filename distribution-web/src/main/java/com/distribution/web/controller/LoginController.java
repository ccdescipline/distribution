package com.distribution.web.controller;

import com.distribution.common.Pojo.vfUser;
import com.distribution.common.responsResult;
import com.distribution.common.Dto.registerDto;
import com.distribution.system.Service.VFRoleService;
import com.distribution.system.Service.VFUserService;
import com.distribution.common.Dto.LoginDto;
import com.distribution.web.Utils.SessionUtils;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Log4j
@RestController
@RequestMapping("/Login")
public class LoginController {

    @Autowired
    private VFUserService userService;

    @Autowired
    private VFRoleService vfRoleService;

    /**
     * 登录
     * @param loginDto
     * @return
     * @throws Exception
     */
    @PostMapping()
    public responsResult Login(@RequestBody @Validated LoginDto loginDto) throws Exception {
        return userService.Login(loginDto.getUsername(),loginDto.getPassword());
    }

    /**
     * 注册
     * @return
     * @throws Exception
     */
    @PostMapping("/loginout")
    public responsResult LoginOut() throws Exception {
        vfUser user = null;
        try{
            user = (vfUser) SessionUtils.GetCurrentUser();
            return responsResult.success(userService.LoginOut(user));
        }catch(NullPointerException e){
            e.printStackTrace();
            return responsResult.error("fail");
        }
        //return responsResult.error("fail");
    }

    /**
     * 注册
     * @param registerDto
     * @return
     */
    @PostMapping("/register")
    public responsResult register(@RequestBody @Validated registerDto registerDto){
        return userService.register(registerDto);
    }

    /**
     * 获取角色信息
     * @return
     */
    @GetMapping("/getroleinfo")
    public responsResult getRoleInfo(){
        return vfRoleService.getRoleinfoByRegister();
    }

    /**
     * 名字是否重名
     * @param username
     * @return
     */
    @GetMapping("/nameisrepeat")
    public responsResult nameIsrepeat(@RequestParam("username") String username){
        return userService.nameIsrepeat(username);
    }
}
