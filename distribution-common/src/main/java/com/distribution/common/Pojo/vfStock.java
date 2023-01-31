package com.distribution.common.Pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName vf_stock
 */
@TableName(value ="vf_stock")
@Data
public class vfStock implements Serializable {
    /**
     * 库存主键
     */
    @TableId
    private String stockid;

    /**
     * 商品ID
     */
    private String goodsid;

    /**
     * 商品数量
     */
    private Integer stockcount;

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