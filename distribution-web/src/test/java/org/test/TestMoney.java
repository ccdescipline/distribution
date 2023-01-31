package org.test;

import com.distribution.system.Service.VFMoneyService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = com.distribution.web.distributionApplication.class)
public class TestMoney {
    @Autowired
    private VFMoneyService vfMoneyService;

    @Test
    public void TestTransfer() throws Exception {
        vfMoneyService.transferMoney("048c0a49-d12d-490a-92a1-e202de4859ee","1506a37d-897e-4572-8a54-e2b2190b731e",new BigDecimal(1000),"测试转账");
    }

    @Test
    public void testMoneyCenterIn() throws Exception {
        vfMoneyService.transferMoneyToMonneyCenter("048c0a49-d12d-490a-92a1-e202de4859ee",new BigDecimal(2),"测试转账");
    }
}
