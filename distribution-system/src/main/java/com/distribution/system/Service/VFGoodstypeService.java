package com.distribution.system.Service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.distribution.common.Pojo.vfGoodstype;
import com.distribution.common.responsResult;
import com.distribution.common.Dto.vfGoodstype.insertGoodstype;
import com.distribution.common.Dto.vfGoodstype.updateGoodstype;

public interface VFGoodstypeService extends IService<vfGoodstype> {
    /**
     * 添加商品分类
     * @param insertGoodstype
     * @return
     */
    responsResult insertGoodstype(insertGoodstype insertGoodstype);

    /**
     *
     * @param updateGoodstype
     * @return
     */
    responsResult updateGoodstype(updateGoodstype updateGoodstype);

    /**
     * 获取激活状态商品品名
     * @return
     */
    responsResult getactiveinfo();
}
