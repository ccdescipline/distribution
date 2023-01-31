package com.distribution.common.Pojo.Vo.supplier;

import com.distribution.common.Pojo.vfGoodsorder;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;


//@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(value = {"supplierid","dealerid"})
@Data
public class vfDealerQueryGoodsOrderVo  extends vfGoodsorder {
    String suppliername;

    List<vfSupplierQueryGoodsOrderInfo> orderinfo;
}
