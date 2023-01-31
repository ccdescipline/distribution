package com.distribution.common.Pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName vf_receiveaddress
 */
@TableName(value ="vf_receiveaddress")
@Data
public class vfReceiveaddress implements Serializable {
    /**
     * 收货地址主键
     */
    @TableId
    private String receiveaddressid;

    /**
     * 省
     */
    private String provinceaddress;

    /**
     * 市
     */
    private String cityaddress;

    /**
     * 区
     */
    private String areaaddress;

    /**
     * 详细住址
     */
    private String detailaddress;

    /**
     * 邮编
     */
    private Integer postalcode;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 收件人
     */
    private String receivename;

    /**
     * 是否默认收货地址
     */
    private Boolean isdefaultadrress;

    /**
     * 创建时间
     */
    private Date createtime;

    /**
     * 修改时间
     */
    private Date updatetime;

    /**
     * 所属ID
     */
    private String userid;

    /**
     * 状态（0 弃用 1启用）
     */
    private Integer status;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}