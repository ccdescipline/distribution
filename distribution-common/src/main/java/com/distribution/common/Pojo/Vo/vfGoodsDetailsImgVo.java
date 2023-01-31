package com.distribution.common.Pojo.Vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@TableName(value ="vf_goodsdetailsimg")
@Data
public class vfGoodsDetailsImgVo implements Serializable {

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
    private Integer index;



    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
