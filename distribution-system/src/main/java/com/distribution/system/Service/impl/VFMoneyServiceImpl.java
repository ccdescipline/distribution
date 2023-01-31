package com.distribution.system.Service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.distribution.common.Pojo.vfMoneyinfo;
import com.distribution.common.Pojo.vfUser;
import com.distribution.common.mapper.vfMoneyinfoMapper;
import com.distribution.common.mapper.vfUserMapper;
import com.distribution.system.Service.VFMoneyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Service
public class VFMoneyServiceImpl implements VFMoneyService {
    @Autowired
    private vfMoneyinfoMapper vfMoneyinfoMapper;

    @Autowired
    private vfUserMapper vfUserMapper;

    @Value("${MoneyConfig.MoneyCenterUserID}")
    private String MoneyUserID;


    @Override
    @Transactional(rollbackFor=Exception.class)
    public void transferMoney(String sourceUserId, String destUserID, BigDecimal money,String Detail) throws Exception {
        //判断钱够不够
        vfUser sourceUser = vfUserMapper.selectOne(Wrappers.<vfUser>lambdaQuery().eq(com.distribution.common.Pojo.vfUser::getUserid, sourceUserId));
        vfUser destUser = vfUserMapper.selectOne(Wrappers.<vfUser>lambdaQuery().eq(com.distribution.common.Pojo.vfUser::getUserid, destUserID));

        //判断小于
        if(money.compareTo(sourceUser.getMoney())!=-1){
            throw new Exception("余额不足");
        }

        sourceUser.setMoney(sourceUser.getMoney().subtract(money));
        destUser.setMoney(destUser.getMoney().add(money));

        //更新余额记录
        if(vfUserMapper.updateById(sourceUser) != 1){
            throw new Exception("更新 sourceUser 失败");
        }
        if(vfUserMapper.updateById(destUser) != 1){
            throw new Exception("更新 destUser 失败");
        }

        //生成转账信息
        vfMoneyinfo vfMoneyinfo = new vfMoneyinfo();
        vfMoneyinfo.setMoneyinfoid(UUID.randomUUID().toString());
        vfMoneyinfo.setSourceuserid(sourceUserId);
        vfMoneyinfo.setDestuserid(destUserID);
        vfMoneyinfo.setTransfermoney(money);
        vfMoneyinfo.setCreatetime(new Date());
        vfMoneyinfo.setSouceuserbanlance(sourceUser.getMoney());
        vfMoneyinfo.setDestuserbanlance(destUser.getMoney());
        vfMoneyinfo.setDetail(Detail);

        //添加转账信息
        if(vfMoneyinfoMapper.insert(vfMoneyinfo)!=1){
            throw new Exception("更新 sourceUser 失败");
        }
    }

    @Override
    public void transferMoneyToMonneyCenter(String sourceUserId, BigDecimal money,String Detail) throws Exception {
        this.transferMoney(sourceUserId,MoneyUserID,money,Detail);
    }

    @Override
    public void transferMoneyByMonneyCenter(String DestUserId, BigDecimal money,String Detail) throws Exception {
        this.transferMoney(MoneyUserID,DestUserId,money,Detail);
    }
}
