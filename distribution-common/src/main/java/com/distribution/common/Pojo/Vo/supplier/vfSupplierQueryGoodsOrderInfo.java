package com.distribution.common.Pojo.Vo.supplier;

import com.distribution.common.Pojo.vfGoodsorderinfo;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class vfSupplierQueryGoodsOrderInfo extends vfGoodsorderinfo {
    String goodsname;
    String goodsshowimg;
}
