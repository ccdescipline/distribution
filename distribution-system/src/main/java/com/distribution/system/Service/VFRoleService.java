package com.distribution.system.Service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.distribution.common.Pojo.vfRole;
import com.distribution.common.Pojo.vfUser;
import com.distribution.common.responsResult;

public interface VFRoleService extends IService<vfRole> {
    /**
     * 获取角色信息
     * @return
     */
    responsResult getRoleinfoByRegister();
}
