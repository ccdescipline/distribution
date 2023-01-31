package com.distribution.common.Pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName vf_goodstype
 */
@TableName(value ="vf_goodstype")
@Data
public class vfGoodstype implements Serializable {
    /**
     * 商品类型主键
     */
    @TableId
    private String goodstypeid;

    /**
     * 类型名
     */
    private String goodstypename;

    /**
     * 父类型ID
     */
    private String goodsparentid;

    /**
     * 状态（1 启用 0弃用）
     */
    private int status;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}