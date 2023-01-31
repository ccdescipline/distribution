package com.distribution.system.Service;

import java.math.BigDecimal;

public interface VFMoneyService {
    /**
     * 转账操作
     * @param sourceUserId
     * @param destUserID
     * @param money
     * @return
     */
    void transferMoney(String sourceUserId, String destUserID, BigDecimal money,String Detail) throws Exception;

    /**
     * 划账中心转账
     * @param sourceUserId
     * @param money
     * @throws Exception
     */
    void transferMoneyToMonneyCenter(String sourceUserId, BigDecimal money,String Detail) throws Exception;

    void transferMoneyByMonneyCenter(String DestUserId, BigDecimal money,String Detail) throws Exception;
}
