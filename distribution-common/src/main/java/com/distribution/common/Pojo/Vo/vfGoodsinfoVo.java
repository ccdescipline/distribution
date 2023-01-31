package com.distribution.common.Pojo.Vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@TableName(value ="vf_goodsinfo")
@Data
public class vfGoodsinfoVo implements Serializable {
    /**
     * 商品信息主键
     */
    @TableId
    private String goodsinfo;

    /**
     * 商品展示图片
     */
    private String goodsshowimg;

    private List<vfGoodsDetailsImgVo> vfGoodsDetailsImgs;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
