package com.distribution.common.Dto.vfGoodsOrder.Supplier.SupplierProcGoodOrder;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class reqSupplierProcGoodsOrderBase {
    @NotNull(message = "订单编号不能为空")
    String orderid;
}
