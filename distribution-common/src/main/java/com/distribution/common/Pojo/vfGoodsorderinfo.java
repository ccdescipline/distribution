package com.distribution.common.Pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Data;

/**
 * 
 * @TableName vf_goodsorderinfo
 */
@TableName(value ="vf_goodsorderinfo")
@Data
public class vfGoodsorderinfo implements Serializable {
    /**
     * 主键
     */
    @TableId
    private String goodsinfoid;

    /**
     * 订单编号
     */
    private String goodsorderid;

    /**
     * 商品信息
     */
    private String goodsid;

    /**
     * 商品单价
     */
    private BigDecimal goodsprice;

    /**
     * 采购数量
     */
    private Integer goodscount;

    /**
     * 商品包装
     */
    private String goodspackage;

    /**
     * 状态 （ 1 启用 0 禁用）
     */
    private Integer status;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}