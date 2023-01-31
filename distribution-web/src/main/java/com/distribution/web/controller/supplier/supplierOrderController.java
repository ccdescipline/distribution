package com.distribution.web.controller.supplier;

import com.distribution.common.Dto.vfGoodsOrder.Supplier.reqCancleGoodsOrderDto;
import com.distribution.common.Dto.vfGoodsOrder.Supplier.SupplierProcGoodOrder.reqConfirmGoodsOrderDto;
import com.distribution.common.Dto.vfGoodsOrder.Supplier.SupplierProcGoodOrder.reqSendGoodsOrderDto;
import com.distribution.common.Dto.vfGoodsOrder.Supplier.reqSupplierQueryGoodsOrderDto;
import com.distribution.common.responsResult;
import com.distribution.system.Service.SupplierService;
import com.distribution.web.Utils.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/supplierOrder")
@PreAuthorize("hasAuthority('ROLE_SUPPLIER')")
public class supplierOrderController {
    @Autowired
    private SupplierService supplierService;

    /**
     * 供应商查看自己订单
     * @param reqSupplierQueryGoodsOrderDto
     * @return
     */
    @GetMapping("/queryGoodsOrder")
    public responsResult queryGoodsOrder(reqSupplierQueryGoodsOrderDto reqSupplierQueryGoodsOrderDto){
        return supplierService.queryGoodsOrder(SessionUtils.GetCurrentUser(),reqSupplierQueryGoodsOrderDto);
    }

    /**
     * 确认订单
     * @param vfConfirmGoodsOrderDto
     * @return
     */
    @PostMapping("/confirmOrder")
    public responsResult confirmOrder(@RequestBody @Validated reqConfirmGoodsOrderDto vfConfirmGoodsOrderDto){
        return supplierService.confirmOrder(SessionUtils.GetCurrentUser(),vfConfirmGoodsOrderDto);
    }

    /**
     * 发货
     * @param reqSendGoodsOrderDto
     * @return
     */
    @PostMapping("/orderSend")
    public responsResult orderSend(@RequestBody @Validated reqSendGoodsOrderDto reqSendGoodsOrderDto){
        return supplierService.orderSend(SessionUtils.GetCurrentUser(),reqSendGoodsOrderDto);
    }

    /**
     * 取消订单
     * @param reqCancleGoodsOrderDto
     * @return
     */
    @PostMapping("/orderCancle")
    public responsResult orderCancle(@RequestBody @Validated reqCancleGoodsOrderDto reqCancleGoodsOrderDto){
        return supplierService.orderCancle(SessionUtils.GetCurrentUser(),reqCancleGoodsOrderDto);
    }
}
