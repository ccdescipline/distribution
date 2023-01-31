package com.distribution.system.Service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.distribution.common.Dto.vfGoodsOrder.Supplier.reqCancleGoodsOrderDto;
import com.distribution.common.Dto.vfGoodsOrder.Supplier.SupplierProcGoodOrder.reqSendGoodsOrderDto;
import com.distribution.common.Dto.vfGoodsOrder.Supplier.reqSupplierQueryGoodsOrderDto;
import com.distribution.common.Dto.vfGoodsOrder.Supplier.resSupplierQueryGoodsOrderDto;
import com.distribution.common.Pojo.StatusEnum.goodsOrderStatus;
import com.distribution.common.Dto.vfGoodsOrder.Supplier.SupplierProcGoodOrder.reqConfirmGoodsOrderDto;
import com.distribution.common.Pojo.Vo.supplier.vfSupplierQueryGoodsOrderInfo;
import com.distribution.common.Pojo.Vo.supplier.vfSupplierQueryGoodsOrderVo;
import com.distribution.common.Pojo.vfUser;
import com.distribution.common.Utils.MinioUtils;
import com.distribution.common.mapper.vfGoodsorderMapper;
import com.distribution.common.responsResult;
import com.distribution.system.Service.SupplierService;
import io.minio.errors.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SupplierServiceImpl extends ProcGoodsOrderBase implements SupplierService {
    @Autowired
    private vfGoodsorderMapper vfGoodsorderMapper;

    @Autowired
    private MinioUtils minioUtils;

    @Override
    public responsResult queryGoodsOrder(vfUser vfUser, reqSupplierQueryGoodsOrderDto reqSupplierQueryGoodsOrderDto) {
        //配置分页
        Page page = Page.of(reqSupplierQueryGoodsOrderDto.getIndex(), reqSupplierQueryGoodsOrderDto.getPage());

        List<vfSupplierQueryGoodsOrderVo> goodsOrderBuSupplier
                = vfGoodsorderMapper.getGoodsOrderBuSupplier(page, reqSupplierQueryGoodsOrderDto,vfUser);

        List<vfSupplierQueryGoodsOrderVo> collect1 = goodsOrderBuSupplier.stream().map((x) -> {
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
     * 确认订单
     * @param vfUser
     * @param vfConfirmGoodsOrderDto
     * @return
     */
    @Override
    public responsResult confirmOrder(vfUser vfUser, reqConfirmGoodsOrderDto vfConfirmGoodsOrderDto) {
//        vfGoodsorder vfGoodsorder = vfGoodsorderMapper.selectOne(Wrappers.<vfGoodsorder>lambdaQuery()
//                .eq(com.distribution.common.Pojo.vfGoodsorder::getOrderid, vfConfirmGoodsOrderDto.getOrderid()));
//
//        //订单检查
//        // 是不是自己
//        if(!vfGoodsorder.getSupplierid().equals(vfUser.getUserid())){
//            return responsResult.error("订单归属错误");
//        }
//
//        //订单状态必须是已下单
//        if(vfGoodsorder.getStatus() != goodsOrderStatus.InOrderCreate.getStatus()){
//            return responsResult.error("订单状态错误");
//        }
//
//        //修改状态
//        vfGoodsorder.setStatus(goodsOrderStatus.InSupplierConfirm.getStatus());
//
//        if(vfGoodsorderMapper.updateById(vfGoodsorder) == 1){
//            return responsResult.success("确认订单成功");
//        }else {
//            return responsResult.error("确认订单失败");
//        }

        return this.orderCheckBySupplier(vfUser,vfConfirmGoodsOrderDto.getOrderid(),
                goodsOrderStatus.InOrderCreate,goodsOrderStatus.InSupplierConfirm,null);
    }

    /**
     * 发货
     * @param vfUser
     * @param reqSendGoodsOrderDto
     * @return
     */
    @Override
    public responsResult orderSend(vfUser vfUser, reqSendGoodsOrderDto reqSendGoodsOrderDto) {
//        vfGoodsorder vfGoodsorder = vfGoodsorderMapper.selectOne(Wrappers.<vfGoodsorder>lambdaQuery()
//                .eq(com.distribution.common.Pojo.vfGoodsorder::getOrderid, reqSendGoodsOrderDto.getOrderid()));
//
//        //订单检查
//
//        // 是不是自己
//        if(!vfGoodsorder.getSupplierid().equals(vfUser.getUserid())){
//            return responsResult.error("订单归属错误");
//        }
//
//        //订单状态必须是已接单
//        if(vfGoodsorder.getStatus() != goodsOrderStatus.InSupplierConfirm.getStatus()){
//            return responsResult.error("订单状态错误");
//        }
//
//        //修改状态
//        vfGoodsorder.setStatus(goodsOrderStatus.InSupplierSend.getStatus());
//
//        if(vfGoodsorderMapper.updateById(vfGoodsorder) == 1){
//            return responsResult.success("发货成功");
//        }else {
//            return responsResult.error("发货失败");
//        }
        return this.orderCheckBySupplier(vfUser,reqSendGoodsOrderDto.getOrderid(),
                goodsOrderStatus.InSupplierConfirm,goodsOrderStatus.InSupplierSend,(x)->{
                    x.setTrackingnumber(reqSendGoodsOrderDto.getTrackingnumber());
                    return x;
                });
    }

    /**
     * 订单取消
     * @param vfUser
     * @param reqCancleGoodsOrderDto
     * @return
     */
    @Override
    public responsResult orderCancle(vfUser vfUser, reqCancleGoodsOrderDto reqCancleGoodsOrderDto) {
        return super.orderCancleBySupplier(vfUser,reqCancleGoodsOrderDto);
    }



}
