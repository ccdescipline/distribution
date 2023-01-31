package com.distribution.common.Pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import lombok.Data;

import javax.validation.constraints.Min;

/**
 * 
 * @TableName vf_goods
 */
@TableName(value ="vf_goods")
@Data
public class vfGoods implements Serializable {
    /**
     * 主键
     */
    @TableId
    private String goodsid;

    /**
     * 商品名称
     */
    private String goodsname;

    /**
     * 商品类型
     */
    private String goodstype;

    /**
     * 商品价格
     */
    private BigDecimal goodsprice;

    /**
     * 商品包装类型
     */
    private String goodspackage;

    /**
     * 商品状态（0 下架 1 上架 -1 弃用）
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

    private String createuserid;

    private String goodsinfoid;


    private int count;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}