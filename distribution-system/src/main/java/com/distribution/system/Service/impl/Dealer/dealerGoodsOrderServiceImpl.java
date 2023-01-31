package com.distribution.system.Service.impl.Dealer;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.distribution.common.Dto.vfGoodsOrder.Supplier.DealerProcGoodsOrder.reqReceiveGoodsOrderDto;
import com.distribution.common.Dto.vfGoodsOrder.Supplier.reqCancleGoodsOrderDto;
import com.distribution.common.Dto.vfGoodsOrder.Supplier.reqDealerQueryGoodsorderDto;
import com.distribution.common.Dto.vfGoodsOrder.Supplier.resSupplierQueryGoodsOrderDto;
import com.distribution.common.Pojo.StatusEnum.goodsOrderStatus;
import com.distribution.common.Pojo.Vo.supplier.vfDealerQueryGoodsOrderVo;
import com.distribution.common.Pojo.Vo.supplier.vfSupplierQueryGoodsOrderInfo;
import com.distribution.common.Pojo.Vo.supplier.vfSupplierQueryGoodsOrderVo;
import com.distribution.common.Pojo.vfGoodsorder;
import com.distribution.common.Pojo.vfUser;
import com.distribution.common.Utils.MinioUtils;
import com.distribution.common.mapper.vfGoodsorderMapper;
import com.distribution.common.responsResult;
import com.distribution.system.Service.dealerGoodsOrderService;
import com.distribution.system.Service.impl.ProcGoodsOrderBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class dealerGoodsOrderServiceImpl extends ProcGoodsOrderBase implements dealerGoodsOrderService {
    @Autowired
    private vfGoodsorderMapper vfGoodsorderMapper;

    @Autowired
    private MinioUtils minioUtils;

    @Override
    public responsResult queryGoodsOrder(vfUser vfUser, reqDealerQueryGoodsorderDto reqDealerQueryGoodsorderDto) {
        //配置分页
        Page page = Page.of(reqDealerQueryGoodsorderDto.getIndex(), reqDealerQueryGoodsorderDto.getPage());

        List<vfDealerQueryGoodsOrderVo> goodsOrderBuSupplier
                = vfGoodsorderMapper.getGoodsOrderBuDealer(page, reqDealerQueryGoodsorderDto,vfUser);

        List<vfDealerQueryGoodsOrderVo> collect1 = goodsOrderBuSupplier.stream().map((x) -> {
            List<vfSupplierQueryGoodsOrderInfo> orderinfo = x.getOrderinfo();

            List<vfSupplierQueryGoodsOrderInfo> collect = orderinfo.stream().map((vfSupplierQueryGoodsOrderInfo) -> {
                try {
                    vfSupplierQueryGoodsOrderInfo.setGoodsshowimg(minioUtils.getObjectUrlByName(vfSupplierQueryGoodsOrderInfo.getGoodsshowimg()));
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

                return vfSupplierQueryGoodsOrderInfo;
            }).collect(Collectors.toList());

            x.setOrderinfo(collect);


            //BeanUtil.copyProperties(x,vfSupplierQueryGoodsOrderVo);
            return x;
        }).collect(Collectors.toList());


        return responsResult.success(resSupplierQueryGoodsOrderDto.setdata(collect1,page.getPages()));
    }

    /**
     * 收货
     * @param vfUser
     * @param reqReceiveGoodsOrderDto
     * @return
     */
    @Override
    public responsResult receiveGoodsOrder(vfUser vfUser, reqReceiveGoodsOrderDto reqReceiveGoodsOrderDto) {
        return this.orderCheckByDealer(vfUser,reqReceiveGoodsOrderDto.getOrderid(),
                goodsOrderStatus.InSupplierSend,goodsOrderStatus.InDealerReceive,null);
    }

    /**
     * 订单取消
     * @param vfUser
     * @param reqCancleGoodsOrderDto
     * @return
     */
    @Override
    public responsResult cancleGoodsOrder(vfUser vfUser, reqCancleGoodsOrderDto reqCancleGoodsOrderDto) {
        return this.orderCancleByDealer(vfUser,reqCancleGoodsOrderDto);
    }


}
