package com.distribution.system.Service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.distribution.common.Dto.vfStock.goodsinStockDto;
import com.distribution.common.Pojo.vfStock;
import com.distribution.common.responsResult;

public interface VFStockService extends IService<vfStock> {

    /**
     * 供应商入库
     * @param goodsinStockDto
     * @return
     */
    responsResult goodsinStockbysupplier(goodsinStockDto goodsinStockDto);


}
