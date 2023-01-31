package org.test;

import com.alibaba.fastjson.JSON;
import com.distribution.common.Dto.vfGoods.reqQueryGoods;
import com.distribution.common.mapper.vfGoodsMapper;
import com.distribution.common.responsResult;
import com.distribution.system.Service.impl.VFGoodsServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = com.distribution.web.distributionApplication.class)
public class testgetgoodinfo {
    @Autowired
    private VFGoodsServiceImpl vfGoodsService;

    @Autowired
    private vfGoodsMapper vfGoodsMapper ;

    @Test
    public void testGoodinfo(){
        reqQueryGoods reqQueryGoods = new reqQueryGoods();
        reqQueryGoods.setIndex(1);
        reqQueryGoods.setPage(100);
        //reqQueryGoods.setGoodsname("油");
        reqQueryGoods.setStartprice(new BigDecimal(1));
        reqQueryGoods.setEndprice(new BigDecimal(50));
        reqQueryGoods.setEndtime(LocalDateTime.now());
        reqQueryGoods.setStatus(1);
        reqQueryGoods.setStarttime(LocalDateTime.of(2022,11,1,0,0));
        vfGoodsService.getgoodinfo(reqQueryGoods);
    }

    @Test
    public void TestgetgoodsinfoByDealer(){
        reqQueryGoods reqQueryGoods = new reqQueryGoods();
        reqQueryGoods.setIndex(1);
        reqQueryGoods.setPage(100);
        vfGoodsService.getgoodinfoByDealer(reqQueryGoods);
    }
}
