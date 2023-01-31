package com.distribution.common.Dto.vfGoodsOrder.Supplier;

import com.distribution.common.Dto.DtoQuery.requestQueryDtoBase;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class reqDealerQueryGoodsorderDto extends requestQueryDtoBase {
    @NotNull(message = "订单状态不能为空")
    Integer status;
}
