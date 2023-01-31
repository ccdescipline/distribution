package com.distribution.web.controller.Dealer;

import com.distribution.common.Dto.vfGoodsOrder.Supplier.DealerProcGoodsOrder.reqReceiveGoodsOrderDto;
import com.distribution.common.Dto.vfGoodsOrder.Supplier.reqCancleGoodsOrderDto;
import com.distribution.common.Dto.vfGoodsOrder.Supplier.reqDealerQueryGoodsorderDto;
import com.distribution.common.Dto.vfGoodsOrder.Supplier.reqSupplierQueryGoodsOrderDto;
import com.distribution.common.responsResult;
import com.distribution.system.Service.dealerGoodsOrderService;
import com.distribution.web.Utils.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dealerOrder")
@PreAuthorize("hasAuthority('ROLE_DELAER')")
public class dealerOrderController {
    @Autowired
    private dealerGoodsOrderService dealerGoodsOrderService;

    /**
     * 经销商查看自己订单
     * @param
     * @return
     */
    @GetMapping("/queryGoodsOrder")
    public responsResult queryGoodsOrder(reqDealerQueryGoodsorderDto reqDealerQueryGoodsorderDto){
        return dealerGoodsOrderService.queryGoodsOrder(SessionUtils.GetCurrentUser(),reqDealerQueryGoodsorderDto);
    }

    /**
     * 收货
     * @param reqReceiveGoodsOrderDto
     * @return
     */
    @PostMapping("receiveGoodsOrder")
    public responsResult receiveGoodsOrder(@RequestBody @Validated reqReceiveGoodsOrderDto reqReceiveGoodsOrderDto){
        return dealerGoodsOrderService.receiveGoodsOrder(SessionUtils.GetCurrentUser(),reqReceiveGoodsOrderDto);
    }

    /**
     * 取消订单
     * @param reqCancleGoodsOrderDto
     * @return
     */
    @PostMapping("cancleGoodsOrder")
    public responsResult cancleGoodsOrder(@RequestBody @Validated reqCancleGoodsOrderDto reqCancleGoodsOrderDto){
        return dealerGoodsOrderService.cancleGoodsOrder(SessionUtils.GetCurrentUser(),reqCancleGoodsOrderDto);
    }
}
