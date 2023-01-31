package com.distribution.common.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.distribution.common.Pojo.Vo.supplier.vfSupplierQueryGoodsOrderInfo;
import com.distribution.common.Pojo.vfGoodsorderinfo;
import com.distribution.common.Pojo.vfGoodstype;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* @author admin
* @description 针对表【vf_goodsorderinfo】的数据库操作Mapper
* @createDate 2022-12-12 21:24:58
* @Entity com.distribution.common.Pojo.vfGoodsorderinfo
*/
@Repository
public interface vfGoodsorderinfoMapper extends BaseMapper<vfGoodsorderinfo> {
    /**
     * 根据订单号找订单信息
     * @return
     */
    List<vfSupplierQueryGoodsOrderInfo> getgoodsorderinfoByOrderID(@Param("goodsOrderId") String goodsOrderId);
}




