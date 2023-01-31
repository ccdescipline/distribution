package com.distribution.common.Pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName vf_goodsorder
 */
@TableName(value ="vf_goodsorder")
@Data
public class vfGoodsorder implements Serializable {
    /**
     * 订单编号
     */
    @TableId
    private String orderid;

    /**
     * 供应商ID
     */
    private String supplierid;

    /**
     * 经销商ID
     */
    private String dealerid;

    /**
     * 快递单号
     */
    private String trackingnumber;

    /**
     * 收货地址
     */
    private String receiveaddress;

    /**
     * 当前状态（-1 订单取消 0 已创建订单  1 供应商已接单 2 供应商已发货 3 经销商已收货）
     */
    private Integer status;

    /**
     * 创建时间
     */
    private LocalDateTime createtime;

    /**
     * 更新时间
     */
    private LocalDateTime updatetime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}