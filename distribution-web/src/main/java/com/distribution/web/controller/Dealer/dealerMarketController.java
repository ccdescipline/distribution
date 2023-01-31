package com.distribution.web.controller.Dealer;

import com.distribution.common.Dto.ValidList;
import com.distribution.common.Dto.vfGoods.Dealer.reqSubmitGoodsCartDto;
import com.distribution.common.Dto.vfGoods.reqQueryGoods;
import com.distribution.common.Dto.vfGoods.Dealer.reqQueryGoodsPriceItem;
import com.distribution.common.Dto.vfReceiveadress.insertReceiveaddressDto;
import com.distribution.common.Dto.vfReceiveadress.removeReceiveaddressDto;
import com.distribution.common.Dto.vfReceiveadress.updateReceiveaddressDto;
import com.distribution.common.Pojo.vfUser;
import com.distribution.common.responsResult;
import com.distribution.system.Service.DealerService;
import com.distribution.system.Service.VFGoodsService;
import com.distribution.system.Service.VFReceiveAdressService;
import com.distribution.web.Utils.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dealerMarket")
@PreAuthorize("hasAuthority('ROLE_DELAER')")
public class dealerMarketController {
    @Autowired
    private VFGoodsService vfGoodsService;

    @Autowired
    private DealerService dealerService;

    @Autowired
    private VFReceiveAdressService vfReceiveAdressService;

    /**
     * 获取商品信息
     * @param reqQueryGoods
     * @return
     */
    @GetMapping("/getgoodinfo")
    public responsResult getGoodsinfo(reqQueryGoods reqQueryGoods){
        return vfGoodsService.getgoodinfoByDealer(reqQueryGoods);
    }

    /**
     * 查询购物车商品价格
     * @param reqQueryGoodsPriceItemList
     * @return
     */
    @PostMapping("/queryGoodcartPrice")
    public responsResult queryGoodcartPrice(@RequestBody @Validated ValidList<reqQueryGoodsPriceItem> reqQueryGoodsPriceItemList) throws Exception {
        return dealerService.queryGoodsCartPrice(reqQueryGoodsPriceItemList);
    }

    //结算
    @PostMapping("/submitGoodcartPrice")
    public responsResult submitGoodcartPrice(@RequestBody @Validated reqSubmitGoodsCartDto reqSubmitGoodsCartDto) throws Exception {
        vfUser vfUser = SessionUtils.GetCurrentUser();

        return dealerService.submitGoodsCartPrice(reqSubmitGoodsCartDto,vfUser);
    }

//---------------------------------------------------


    /**
     * 获取收货地址
     * @return
     */
    @GetMapping("/receiveaddress")
    public responsResult getcurrentReceiveAdress(){
        return vfReceiveAdressService.getcurrentReceiveddress(SessionUtils.GetCurrentUser());
    }

    @PostMapping("/receiveaddress")
    public responsResult insetReceiveAdress(@RequestBody insertReceiveaddressDto insertReceiveaddressDto) throws Exception {
        return vfReceiveAdressService.insertReceiveddress(SessionUtils.GetCurrentUser(),insertReceiveaddressDto);
    }

    @PutMapping("/receiveaddress")
    public responsResult updateReceiveAdress(@RequestBody updateReceiveaddressDto updateReceiveaddressDto) throws Exception {
        return vfReceiveAdressService.updateReceiveddress(SessionUtils.GetCurrentUser(),updateReceiveaddressDto);
    }

    @DeleteMapping("/receiveaddress")
    public responsResult removeReceiveAdress(@RequestBody removeReceiveaddressDto removeReceiveaddressDto) throws Exception {
        return vfReceiveAdressService.removeReceiveddress(SessionUtils.GetCurrentUser(),removeReceiveaddressDto);
    }

}
