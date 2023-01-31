package com.distribution.system.Service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.distribution.common.Dto.vfGoodsOrder.Supplier.reqCancleGoodsOrderDto;
import com.distribution.common.Pojo.StatusEnum.goodsOrderStatus;
import com.distribution.common.Pojo.vfGoodsorder;
import com.distribution.common.Pojo.vfUser;
import com.distribution.common.mapper.vfGoodsorderMapper;
import com.distribution.common.responsResult;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.function.Function;

public abstract class ProcGoodsOrderBase {
    @Autowired
    private vfGoodsorderMapper vfGoodsorderMapper;

    protected responsResult orderCheckBySupplier(vfUser vfUser, String orderID,
                                       goodsOrderStatus checkStatus, goodsOrderStatus updateStatus,
                                       Function<vfGoodsorder, vfGoodsorder> Proc_Order
    ){
        vfGoodsorder vfGoodsorder = vfGoodsorderMapper.selectOne(Wrappers.<vfGoodsorder>lambdaQuery()
                .eq(com.distribution.common.Pojo.vfGoodsorder::getOrderid, orderID));

        if(vfGoodsorder==null){
            return responsResult.error("找不到订单");
        }

        // 是不是自己
        if(!vfGoodsorder.getSupplierid().equals(vfUser.getUserid())){
            return responsResult.error("订单归属错误");
        }

        //订单状态
        if(vfGoodsorder.getStatus() != checkStatus.getStatus()){
            return responsResult.error("订单状态错误");
        }

        //修改状态
        vfGoodsorder.setStatus(updateStatus.getStatus());

        if(Proc_Order !=null){
            vfGoodsorder = Proc_Order.apply(vfGoodsorder);
        }

        if(vfGoodsorderMapper.updateById(vfGoodsorder) == 1){
            return responsResult.success(updateStatus.getDescription()+"成功");
        }else {
            return responsResult.error(updateStatus.getDescription()+"失败");
        }
    }

    protected responsResult orderCheckByDealer(vfUser vfUser, String orderID,
                                                 goodsOrderStatus checkStatus, goodsOrderStatus updateStatus,
                                                 Function<vfGoodsorder, vfGoodsorder> Proc_Order
    ){
        vfGoodsorder vfGoodsorder = vfGoodsorderMapper.selectOne(Wrappers.<vfGoodsorder>lambdaQuery()
                .eq(com.distribution.common.Pojo.vfGoodsorder::getOrderid, orderID));

        if(vfGoodsorder==null){
            return responsResult.error("找不到订单");
        }

        // 是不是自己
        if(!vfGoodsorder.getDealerid().equals(vfUser.getUserid())){
            return responsResult.error("订单归属错误");
        }

        //订单状态
        if(vfGoodsorder.getStatus() != checkStatus.getStatus()){
            return responsResult.error("订单状态错误");
        }

        //修改状态
        vfGoodsorder.setStatus(updateStatus.getStatus());

        if(Proc_Order !=null){
            vfGoodsorder = Proc_Order.apply(vfGoodsorder);
        }

        if(vfGoodsorderMapper.updateById(vfGoodsorder) == 1){
            return responsResult.success(updateStatus.getDescription()+"成功");
        }else {
            return responsResult.error(updateStatus.getDescription()+"失败");
        }
    }

    protected responsResult orderCancleBySupplier(vfUser vfUser, reqCancleGoodsOrderDto reqCancleGoodsOrderDto) {
        return this.orderCheckBySupplier(vfUser,reqCancleGoodsOrderDto.getOrderid(),
                goodsOrderStatus.InOrderCreate,goodsOrderStatus.InOrderClose,null);
    }
    protected responsResult orderCancleByDealer(vfUser vfUser, reqCancleGoodsOrderDto reqCancleGoodsOrderDto) {
        return this.orderCheckByDealer(vfUser,reqCancleGoodsOrderDto.getOrderid(),
                goodsOrderStatus.InOrderCreate,goodsOrderStatus.InOrderClose,null);
    }

}
