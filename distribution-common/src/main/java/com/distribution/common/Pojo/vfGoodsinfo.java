package com.distribution.common.Pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName vf_goodsinfo
 */
@TableName(value ="vf_goodsinfo")
@Data
public class vfGoodsinfo implements Serializable {
    /**
     * 商品信息主键
     */
    @TableId
    private String goodsinfo;

    /**
     * 商品展示图片
     */
    private String goodsshowimg;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}