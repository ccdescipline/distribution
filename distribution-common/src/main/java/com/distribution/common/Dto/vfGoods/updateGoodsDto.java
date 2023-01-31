package com.distribution.common.Dto.vfGoods;

import lombok.Data;

import javax.validation.constraints.Min;

@Data
public class updateGoodsDto {
    String goodsid;
    String goodsname;
    String goodstype;
    String goodsprice;
    String goodspackage;
    int status;
    @Min( value = 0,message = "商品数量必须大于0")
    int count;
}
