package com.distribution.system.Service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.distribution.common.Dto.vfStock.goodsinStockDto;
import com.distribution.common.Pojo.vfStock;
import com.distribution.common.mapper.VfStockMapper;

import com.distribution.common.responsResult;
import com.distribution.system.Service.VFStockService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class VFStockServiceImpl extends ServiceImpl<VfStockMapper, vfStock> implements VFStockService {

    @Override
    public responsResult goodsinStockbysupplier(goodsinStockDto goodsinStockDto) {
        //初始化列
        goodsinStockDto.setGoodsid(UUID.randomUUID().toString());
        goodsinStockDto.setCreatetime(LocalDateTime.now());
        goodsinStockDto.setUpdatetime(LocalDateTime.now());

        boolean save = save(goodsinStockDto);

        if(save){
            return responsResult.success("商品入库成功");
        }else {
            return responsResult.error("商品入库失败");
        }
    }
}
