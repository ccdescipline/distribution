package com.distribution.system.Service.impl;

import cn.hutool.core.lang.UUID;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.distribution.common.Pojo.vfGoodstype;
import com.distribution.common.mapper.vfGoodstypeMapper;
import com.distribution.common.responsResult;
import com.distribution.common.Dto.vfGoodstype.insertGoodstype;
import com.distribution.common.Dto.vfGoodstype.updateGoodstype;
import com.distribution.system.Service.VFGoodstypeService;
import org.springframework.stereotype.Service;

@Service
public class VFGoodtypeServiceImpl extends ServiceImpl<vfGoodstypeMapper, vfGoodstype> implements VFGoodstypeService {
    @Override
    public responsResult insertGoodstype(insertGoodstype insertGoodstype) {

        if(!hansparent(insertGoodstype.getParentid())){
            return responsResult.error("未找到父商品！");
        }


        vfGoodstype goodstype = new vfGoodstype();
        goodstype.setGoodstypeid(UUID.randomUUID().toString());
        goodstype.setGoodstypename(insertGoodstype.getGoodsname());
        goodstype.setGoodsparentid(insertGoodstype.getParentid());
        goodstype.setStatus(1);

        boolean save = save(goodstype);

        if(save){
            return responsResult.success("添加成功");
        }else {
            return responsResult.error("添加失败");
        }
    }

    @Override
    public responsResult updateGoodstype(updateGoodstype updateGoodstype) {
        if(!hansparent(updateGoodstype.getGoodsparentid())){
            return responsResult.error("未找到父商品！");
        }


        vfGoodstype vfGoodstype = updateGoodstype;

        boolean update = updateById(vfGoodstype);

        if(update){
            return responsResult.success("修改成功");
        }else {
            return responsResult.error("修改失败");
        }
    }

    @Override
    public responsResult getactiveinfo() {
        return responsResult.success(list(Wrappers.<vfGoodstype>lambdaQuery().eq(vfGoodstype::getStatus,1)));
    }

    boolean hansparent(String parentid){
        //先找父元素
        vfGoodstype findgoodstype = getById(parentid);

        if(findgoodstype == null && !parentid.equals("0")){
            return false;
        }else {
            return true;
        }
    }
}
