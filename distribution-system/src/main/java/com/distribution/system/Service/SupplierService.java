package com.distribution.system.Service;

import com.distribution.common.Dto.vfGoodsOrder.Supplier.reqCancleGoodsOrderDto;
import com.distribution.common.Dto.vfGoodsOrder.Supplier.SupplierProcGoodOrder.reqSendGoodsOrderDto;
import com.distribution.common.Dto.vfGoodsOrder.Supplier.reqSupplierQueryGoodsOrderDto;
import com.distribution.common.Dto.vfGoodsOrder.Supplier.SupplierProcGoodOrder.reqConfirmGoodsOrderDto;
import com.distribution.common.Pojo.vfUser;
import com.distribution.common.responsResult;

/**
 * 供应商操作
 */
public interface SupplierService {
    /**
     * 查询订单
     * @param vfUser
     * @param reqSupplierQueryGoodsOrderDto
     * @return
     */
    responsResult queryGoodsOrder(vfUser vfUser, reqSupplierQueryGoodsOrderDto reqSupplierQueryGoodsOrderDto);

    /**
     * 供应商确认订单
     * @param vfUser
     * @param vfConfirmGoodsOrderDto
     * @return
     */
    responsResult confirmOrder(vfUser vfUser, reqConfirmGoodsOrderDto vfConfirmGoodsOrderDto);

    /**
     * 发货
     * @param vfUser
     * @param reqSendGoodsOrderDto
     * @return
     */
    responsResult orderSend(vfUser vfUser, reqSendGoodsOrderDto reqSendGoodsOrderDto);

    /**
     * 订单取消
     * @param vfUser
     * @param reqCancleGoodsOrderDto
     * @return
     */
    responsResult orderCancle(vfUser vfUser, reqCancleGoodsOrderDto reqCancleGoodsOrderDto);
}
