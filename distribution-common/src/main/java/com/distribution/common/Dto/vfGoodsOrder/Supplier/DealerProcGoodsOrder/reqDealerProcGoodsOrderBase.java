package com.distribution.common.Dto.vfGoodsOrder.Supplier.DealerProcGoodsOrder;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class reqDealerProcGoodsOrderBase {
    @NotNull(message = "订单编号不能为空")
    String orderid;
}
