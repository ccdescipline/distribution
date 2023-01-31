package com.distribution.common.Pojo.Vo.supplier;

import com.distribution.common.Pojo.vfGoodsorder;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
//@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(value = {"supplierid","dealerid"})
public class vfSupplierQueryGoodsOrderVo extends vfGoodsorder {

    String dealername;

    List<vfSupplierQueryGoodsOrderInfo> orderinfo;
}
