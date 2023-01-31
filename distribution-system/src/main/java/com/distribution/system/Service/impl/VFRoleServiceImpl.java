package com.distribution.system.Service.impl;

import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.TableInfoHelper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.distribution.common.Pojo.vfRole;
import com.distribution.common.Pojo.vfUser;
import com.distribution.common.mapper.vfRoleMapper;
import com.distribution.common.responsResult;
import com.distribution.system.Service.VFRoleService;
import com.distribution.system.Service.VFUserService;
import org.apache.ibatis.builder.MapperBuilderAssistant;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class VFRoleServiceImpl extends ServiceImpl<vfRoleMapper, vfRole> implements VFRoleService {
    @Override
    public responsResult getRoleinfoByRegister() {
        TableInfoHelper.initTableInfo(new MapperBuilderAssistant(new MybatisConfiguration(), ""), vfRole.class) ;
        List<Map<String, Object>> maps
                = listMaps(Wrappers.<vfRole>lambdaQuery()
                //查询指定两列
                .select(vfRole::getRoleid,vfRole::getRoleName)
                //查询条件
                .ne(vfRole::getRoleName,"管理员")
                .eq(vfRole::getRoleStatus, 1))
                ;
        return responsResult.success(maps);
    }
}
