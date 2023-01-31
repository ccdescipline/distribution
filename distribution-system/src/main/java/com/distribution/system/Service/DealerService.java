package com.distribution.system.Service;

import com.distribution.common.Dto.vfGoods.Dealer.reqQueryGoodsPriceItem;
import com.distribution.common.Dto.vfGoods.Dealer.reqSubmitGoodsCartDto;
import com.distribution.common.Pojo.vfUser;
import com.distribution.common.responsResult;

import java.util.List;

public interface DealerService {

    /**
     * 查询购物车价格
     * @return
     */
    responsResult queryGoodsCartPrice(List<reqQueryGoodsPriceItem> reqQueryGoodsPriceItemList) throws Exception;

    /**
     * 提交订单
     * @param reqSubmitGoodsCartDto
     * @param vfUser
     * @return
     * @throws Exception
     */
    responsResult submitGoodsCartPrice(reqSubmitGoodsCartDto reqSubmitGoodsCartDto, vfUser vfUser) throws Exception;

}
