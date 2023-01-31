package com.distribution.system.Service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.distribution.common.Dto.ValidList;
import com.distribution.common.Dto.vfGoods.Dealer.reqQueryGoodsPriceItem;
import com.distribution.common.Dto.vfGoods.Dealer.reqSubmitGoodsCartDto;
import com.distribution.common.Pojo.StatusEnum.goodsOrderStatus;
import com.distribution.common.Pojo.vfGoods;
import com.distribution.common.Pojo.vfGoodsorder;
import com.distribution.common.Pojo.vfGoodsorderinfo;
import com.distribution.common.Pojo.vfUser;
import com.distribution.common.mapper.vfGoodsMapper;
import com.distribution.common.mapper.vfGoodsorderMapper;
import com.distribution.common.mapper.vfGoodsorderinfoMapper;
import com.distribution.common.responsResult;
import com.distribution.system.Service.DealerService;
import com.distribution.system.Service.VFMoneyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class DealerServiceImpl implements DealerService {
    @Autowired
    private vfGoodsMapper vfGoodsMapper;

    @Autowired
    private vfGoodsorderinfoMapper vfGoodsorderinfoMapper;

    @Autowired
    private vfGoodsorderMapper vfGoodsorderMapper;

    @Autowired
    private VFMoneyService vfMoneyService;

    @Override
    public responsResult queryGoodsCartPrice(List<reqQueryGoodsPriceItem> reqQueryGoodsPriceItemList) throws Exception {
        BigDecimal totalPrice = new BigDecimal(0);
        for (reqQueryGoodsPriceItem item : reqQueryGoodsPriceItemList) {
            vfGoods one = vfGoodsMapper.selectOne(Wrappers.<vfGoods>lambdaQuery().eq(vfGoods::getGoodsid, item.getGoodsid()));
            if(one==null){
                throw new Exception("其中有商品ID不存在");
            }
            //加上价格


            totalPrice = totalPrice.add(one.getGoodsprice().multiply(new BigDecimal(item.getCount())));
        }



        return responsResult.success(totalPrice);
    }


    @Override
    @Transactional(rollbackFor=Exception.class)
    public responsResult submitGoodsCartPrice(reqSubmitGoodsCartDto reqSubmitGoodsCartDto, vfUser vfUser) throws Exception {

        List<reqQueryGoodsPriceItem> reqQueryGoodsPriceItemList = reqSubmitGoodsCartDto.getReqQueryGoodsPriceItemList();




        //跟据goodsID 查询 商品信息
        List<vfGoods> list = vfGoodsMapper.selectList(Wrappers.<vfGoods>lambdaQuery().in(vfGoods::getGoodsid,
                reqQueryGoodsPriceItemList.stream().map(reqQueryGoodsPriceItem::getGoodsid).collect(Collectors.toList())
        ));

        //判断是否有商品不存在
        if(list.size()!=reqQueryGoodsPriceItemList.size()){
            throw  new Exception("商品数据异常");
        }

        //第一步 分不同的店生成订单
        Map<String, List<vfGoods>> collect = list.stream().collect(Collectors.groupingBy(vfGoods::getCreateuserid));

        for (String key : collect.keySet()) {
            //添加订单信息
            vfGoodsorder vfGoodsorder= new vfGoodsorder();

            vfGoodsorder.setOrderid(UUID.randomUUID().toString());
            vfGoodsorder.setSupplierid(key);
            vfGoodsorder.setDealerid(vfUser.getUserid());
            vfGoodsorder.setTrackingnumber(null);
            vfGoodsorder.setReceiveaddress(reqSubmitGoodsCartDto.getReceiveaddress());
            //订单创建状态
            vfGoodsorder.setStatus(goodsOrderStatus.InOrderCreate.getStatus());

            vfGoodsorder.setCreatetime(LocalDateTime.now());
            vfGoodsorder.setUpdatetime(LocalDateTime.now());

            //添加
            int insert = vfGoodsorderMapper.insert(vfGoodsorder);
            if(insert != 1){
                throw new RuntimeException("创建订单主 失败");
            }

            //第二步 分门别类生成订单信息表
            //为不同的订单添加商品详情
            List<vfGoods> vfGoods = collect.get(key);
            for (vfGoods item: vfGoods){
                //拿该商品的数量
                int currentGoodscount = reqQueryGoodsPriceItemList.stream().filter(x -> {
                    return x.getGoodsid().equals(item.getGoodsid());
                }).findFirst().get().getCount();

                //检查商品库存
                if(currentGoodscount>item.getCount()){
                    throw new Exception(item.getGoodsid() + ":" + item.getGoodsname()+"商品库存不足");
                }

                item.setCount(item.getCount() - currentGoodscount);
                if(vfGoodsMapper.updateById(item)!=1){
                    throw new Exception("修改商品库存失败");
                }


                vfGoodsorderinfo vfGoodsorderinfo = new vfGoodsorderinfo();

                vfGoodsorderinfo.setGoodsinfoid(UUID.randomUUID().toString());
                vfGoodsorderinfo.setGoodsorderid(vfGoodsorder.getOrderid());
                vfGoodsorderinfo.setGoodsid(item.getGoodsid());
                vfGoodsorderinfo.setGoodsprice(item.getGoodsprice());
                vfGoodsorderinfo.setGoodscount(
                        currentGoodscount
                );
                vfGoodsorderinfo.setGoodspackage(item.getGoodspackage());
                vfGoodsorderinfo.setStatus(1);

                //添加
                int vfGoodsorderinfoInsertRes = vfGoodsorderinfoMapper.insert(vfGoodsorderinfo);
                if(vfGoodsorderinfoInsertRes!=1){
                    throw new RuntimeException("创建订单从 失败");
                }
            }
        }

        //转账（先划走钱，等确认收货的时候加上）
        BigDecimal totalMoney = (BigDecimal) this.queryGoodsCartPrice(reqQueryGoodsPriceItemList).getData();
        vfMoneyService.transferMoneyToMonneyCenter(vfUser.getUserid(),totalMoney,
                "订单编号   "+ collect.keySet().stream().collect(Collectors.joining(",")) +"  转账到支付保障账号"
        );

        return responsResult.success("订单创建成功");
    }
}
