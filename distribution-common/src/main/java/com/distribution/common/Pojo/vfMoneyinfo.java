package com.distribution.common.Pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName vf_moneyinfo
 */
@TableName(value ="vf_moneyinfo")
@Data
public class vfMoneyinfo implements Serializable {
    /**
     * 转账信息主键

     */
    @TableId
    private String moneyinfoid;

    /**
     * 源账户
     */
    private String sourceuserid;

    /**
     * 目标账户
     */
    private String destuserid;

    /**
     * 转账金额
     */
    private BigDecimal transfermoney;

    /**
     * 创建时间
     */
    private Date createtime;

    /**
     * 源账户转账后余额
     */
    private BigDecimal souceuserbanlance;

    /**
     * 目标账户转账后余额
     */
    private BigDecimal destuserbanlance;

    /**
     * 备注
     */
    private String detail;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}