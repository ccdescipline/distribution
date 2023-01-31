package com.distribution.common.Pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName vf_goodsdetailsimg
 */
@TableName(value ="vf_goodsdetailsimg")
@Data
public class vfGoodsdetailsimg implements Serializable {
    /**
     * 商品详情图片ID
     */
    @TableId
    private String goodsdetailsimgid;

    /**
     * 商品详情图片
     */
    private String goodsdetailsimg;

    /**
     * 图片顺序
     */
    @TableField("`index`")
    private int index;

    /**
     * 商品信息主键
     */
    private String goodsinfoid;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}