package com.distribution.common.Dto.vfGoodstype;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class insertGoodstype {
    @NotNull( message = "商品类型名称不能为空")
    String goodsname;
    @NotNull( message = "父品种不能为空")
    String parentid;
}
