package com.distribution.common.Dto.vfGoodsOrder.Supplier;

import com.distribution.common.Dto.DtoQuery.requestQueryDtoBase;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class reqSupplierQueryGoodsOrderDto extends requestQueryDtoBase {

    Date updatestarttime;

    Date updateendtime;


    Date createstarttime;

    Date createendtime;

    Integer status;

}
