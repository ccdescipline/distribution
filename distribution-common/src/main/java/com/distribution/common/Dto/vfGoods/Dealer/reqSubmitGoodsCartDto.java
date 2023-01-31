package com.distribution.common.Dto.vfGoods.Dealer;

import com.distribution.common.Dto.ValidList;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class reqSubmitGoodsCartDto {
    @NotEmpty(message = "收货地址不能为空")
    String receiveaddress;

    @Valid
    List<reqQueryGoodsPriceItem> reqQueryGoodsPriceItemList;
}
