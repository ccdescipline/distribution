package com.distribution.web.controller;

import com.distribution.common.Pojo.vfUser;
import com.distribution.common.responsResult;
import com.distribution.common.Dto.vfUser.reqQueryUserDto;
import com.distribution.common.Dto.vfUser.resetPwdDto;
import com.distribution.common.Dto.vfUser.updateUserDto;
import com.distribution.system.Service.VFUserService;
import com.distribution.web.Utils.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/User")
public class VFUserController {

    @Autowired
    private VFUserService userService;

    @RequestMapping("/test")
    public responsResult test(){

        return responsResult.success("测试");
    }

    @GetMapping()
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public responsResult getUserlist(reqQueryUserDto userDto){

        return userService.getUserlist(userDto);
    }

    @GetMapping("/info")
    public responsResult Getinfo(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(principal);
        return userService.getcurrentinfo(
                (vfUser) SessionUtils.GetCurrentUser()
        );
    }

    @PostMapping()
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public responsResult updateinfo(@RequestBody updateUserDto updateUserDto){
        return userService.updateinfo(updateUserDto);
    }

    @PostMapping("/resetpwd")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public responsResult reset (@RequestBody resetPwdDto resetPwdDto){
        return userService.resetpwd(resetPwdDto.getUsername());
    }
}
