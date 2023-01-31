package com.distribution.system.Service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.distribution.common.Dto.vfReceiveadress.insertReceiveaddressDto;
import com.distribution.common.Dto.vfReceiveadress.removeReceiveaddressDto;
import com.distribution.common.Dto.vfReceiveadress.updateReceiveaddressDto;
import com.distribution.common.Pojo.vfReceiveaddress;
import com.distribution.common.Pojo.vfUser;
import com.distribution.common.mapper.vfReceiveaddressMapper;
import com.distribution.common.responsResult;
import com.distribution.system.Service.VFReceiveAdressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class VFReceiveAdressServiceImpl extends ServiceImpl<vfReceiveaddressMapper, vfReceiveaddress> implements VFReceiveAdressService {

    @Autowired
    private vfReceiveaddressMapper vfReceiveaddressMapper;


    /**
     * 获取当前用户的收货地址
     * @param vfUser
     * @return
     */
    @Override
    public responsResult getcurrentReceiveddress(vfUser vfUser) {
        return responsResult.success(vfReceiveaddressMapper.getcurrentReceiveaddressinfo(vfUser));
    }

    /**
     * 添加收货地址
     * @param vfUser
     * @param insertReceiveaddressDto
     * @return
     */
    @Override
    @Transactional(rollbackFor=Exception.class)
    public responsResult insertReceiveddress(vfUser vfUser, insertReceiveaddressDto insertReceiveaddressDto) throws Exception {

        insertReceiveaddressDto.setReceiveaddressid(UUID.randomUUID().toString());
        insertReceiveaddressDto.setCreatetime(new Date());
        insertReceiveaddressDto.setUpdatetime(new Date());
        insertReceiveaddressDto.setStatus(1);
        insertReceiveaddressDto.setUserid(vfUser.getUserid());

        this.checkIsDefaultAddress(vfUser,insertReceiveaddressDto);


        if(save(insertReceiveaddressDto)){
            return responsResult.success("添加成功");
        }else {
            return responsResult.error("添加失败");
        }
    }

    /**
     * 修改收货地址
     * @param vfUser
     * @param updateReceiveaddressDto
     * @return
     */
    @Override
    @Transactional(rollbackFor=Exception.class)
    public responsResult updateReceiveddress(vfUser vfUser, updateReceiveaddressDto updateReceiveaddressDto) throws Exception {
        //判断是不是自己的收货地址
        if(!updateReceiveaddressDto.getReceiveaddressid().equals(vfUser.getUserid())){
            responsResult.error("错误收货地址");
        }

        vfReceiveaddress one = getOne(Wrappers.<vfReceiveaddress>lambdaQuery().
                eq(vfReceiveaddress::getReceiveaddressid, updateReceiveaddressDto.getReceiveaddressid()));

        //只能改8个字段
        one.setProvinceaddress(updateReceiveaddressDto.getProvinceaddress());
        one.setCityaddress(updateReceiveaddressDto.getCityaddress());
        one.setAreaaddress(updateReceiveaddressDto.getAreaaddress());
        one.setDetailaddress(updateReceiveaddressDto.getDetailaddress());
        one.setPostalcode(updateReceiveaddressDto.getPostalcode());
        one.setPhone(updateReceiveaddressDto.getPhone());
        one.setReceivename(updateReceiveaddressDto.getReceivename());
        one.setIsdefaultadrress(updateReceiveaddressDto.getIsdefaultadrress());

        this.checkIsDefaultAddress(vfUser,one);


        one.setUpdatetime(new Date());


        if(updateById(updateReceiveaddressDto)){
            return responsResult.success("修改成功");
        }else {
            return responsResult.error("修改失败");
        }

    }

    /**
     * 移除收货地址
     * @param vfUser
     * @param removeReceiveaddressDto
     * @return
     */
    @Override
    public responsResult removeReceiveddress(vfUser vfUser, removeReceiveaddressDto removeReceiveaddressDto) throws Exception {
        //判断是不是自己的收货地址
        if(!removeReceiveaddressDto.getReceiveaddressid().equals(vfUser.getUserid())){
            responsResult.error("错误收货地址");
        }

        vfReceiveaddress one = getOne(Wrappers.<vfReceiveaddress>lambdaQuery().
                eq(vfReceiveaddress::getReceiveaddressid, removeReceiveaddressDto.getReceiveaddressid()));

        if(one.getIsdefaultadrress()){
            throw new Exception("默认收货地址不能删除");
        }

        one.setStatus(0);

        if(updateById(one)){
            return responsResult.success("移除成功");
        }else {
            return responsResult.error("移除失败");
        }

    }

    @Transactional(rollbackFor=Exception.class)
    public void checkIsDefaultAddress(vfUser vfUser,vfReceiveaddress updateReceiveaddressDto) throws Exception {
        //判断是不是默认收货地址
        List<vfReceiveaddress> list = list(Wrappers.<vfReceiveaddress>lambdaQuery()
                //拿自己的收货地址
                .eq(vfReceiveaddress::getUserid, vfUser.getUserid())
                //排除当前这个
                .ne(vfReceiveaddress::getReceiveaddressid,updateReceiveaddressDto.getReceiveaddressid())
        );
        if(updateReceiveaddressDto.getIsdefaultadrress()){
            //找到其他收货地址，修改
            //新的结果
            List<vfReceiveaddress> collect = list.stream().map(x -> {
                x.setIsdefaultadrress(false);
                return x;
            }).collect(Collectors.toList());
            for (vfReceiveaddress i:
                    collect) {
                if (!updateById(i)){
                    throw new Exception("修改默认收获地址出错");
                }
            }

        }else {
            //如果除此之外没有默认地址就返回错误

            boolean res =false;
            for (vfReceiveaddress i:
                    list) {
                if(i.getIsdefaultadrress()){
                    res = true;
                }
            }

            if(!res){
                throw new Exception("没有其他的默认收获地址");
            }
        }
    }

}
