package com.distribution.common.Dto.vfGoods.Dealer;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class reqQueryGoodsPriceItem {
    @NotNull(message = "商品ID不能为空")
    String goodsid;

    @NotNull(message = "商品数量不能为空")
    @Min(value = 1,message = "商品数量非法")
    int count;
}
