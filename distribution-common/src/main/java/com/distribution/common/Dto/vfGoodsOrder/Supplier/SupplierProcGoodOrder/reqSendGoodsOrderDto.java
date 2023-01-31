package com.distribution.common.Dto.vfGoodsOrder.Supplier.SupplierProcGoodOrder;

import com.distribution.common.Dto.vfGoodsOrder.Supplier.SupplierProcGoodOrder.reqSupplierProcGoodsOrderBase;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class reqSendGoodsOrderDto extends reqSupplierProcGoodsOrderBase {
    @NotNull(message = "快递单号不能为空")
    String trackingnumber;
}
