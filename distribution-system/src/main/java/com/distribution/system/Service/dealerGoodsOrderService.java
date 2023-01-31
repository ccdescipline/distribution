package com.distribution.system.Service;

import com.distribution.common.Dto.vfGoodsOrder.Supplier.DealerProcGoodsOrder.reqReceiveGoodsOrderDto;
import com.distribution.common.Dto.vfGoodsOrder.Supplier.reqCancleGoodsOrderDto;
import com.distribution.common.Dto.vfGoodsOrder.Supplier.reqDealerQueryGoodsorderDto;
import com.distribution.common.Pojo.vfUser;
import com.distribution.common.responsResult;

public interface dealerGoodsOrderService {
    /**
     * 经销商订单查询
     * @param vfUser
     * @param reqDealerQueryGoodsorderDto
     * @return
     */
    responsResult queryGoodsOrder(vfUser vfUser, reqDealerQueryGoodsorderDto reqDealerQueryGoodsorderDto);

    /**
     * 确认订单
     * @param vfUser
     * @param reqReceiveGoodsOrderDto
     * @return
     */
    responsResult receiveGoodsOrder(vfUser vfUser, reqReceiveGoodsOrderDto reqReceiveGoodsOrderDto);

    responsResult cancleGoodsOrder(vfUser vfUser, reqCancleGoodsOrderDto reqCancleGoodsOrderDto);
}
