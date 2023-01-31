package com.distribution.common.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.distribution.common.Dto.vfGoodsOrder.Supplier.reqDealerQueryGoodsorderDto;
import com.distribution.common.Dto.vfGoodsOrder.Supplier.reqSupplierQueryGoodsOrderDto;
import com.distribution.common.Pojo.Vo.supplier.vfDealerQueryGoodsOrderVo;
import com.distribution.common.Pojo.Vo.supplier.vfSupplierQueryGoodsOrderVo;
import com.distribution.common.Pojo.vfGoodsorder;
import com.distribution.common.Pojo.vfGoodsorderinfo;
import com.distribution.common.Pojo.vfUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* @author admin
* @description 针对表【vf_goodsorder】的数据库操作Mapper
* @createDate 2022-12-12 21:59:27
* @Entity com.distribution.common.Pojo.vfGoodsorder
*/
@Repository
public interface vfGoodsorderMapper extends BaseMapper<vfGoodsorder> {

    List<vfSupplierQueryGoodsOrderVo> getGoodsOrderBuSupplier(Page page,
                                                              @Param("reqSupplierQueryGoodsOrderDto") reqSupplierQueryGoodsOrderDto reqSupplierQueryGoodsOrderDto,
                                                              @Param("vfUser") vfUser vfUser
    );

    List<vfDealerQueryGoodsOrderVo> getGoodsOrderBuDealer(Page page,
                                                          @Param("reqDealerQueryGoodsorderDto") reqDealerQueryGoodsorderDto reqDealerQueryGoodsorderDto,
                                                          @Param("vfUser") vfUser vfUser
    );
}




