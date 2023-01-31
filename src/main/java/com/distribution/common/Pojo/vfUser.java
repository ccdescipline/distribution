package com.distribution.common.Pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName vf_user
 */
@TableName(value ="vf_user")
@Data
public class vfUser implements Serializable {
    /**
     * USER主键
     */
    @TableId
    private String userid;

    /**
     * 用户名
     */
    private String username;

    /**
     * 公司名称
     */
    private String companyname;

    /**
     * 密码
     */
    private String userpwd;

    /**
     * 盐
     */
    private String salt;

    /**
     * 性别
     */
    private String usersex;

    /**
     * 邮箱
     */
    private String useremail;

    /**
     * 手机号
     */
    private String userphone;

    /**
     * 角色ID
     */
    private String roleid;

    /**
     * 用户状态(1 可用，0 禁用)
     */
    private Integer userstatus;

    /**
     * 金额
     */
    private BigDecimal money;

    /**
     * 上次登陆时间
     */
    private Date lastlogintime;

    /**
     * 创建时间
     */
    private Date createtime;

    /**
     * 更新时间
     */
    private Date updatetime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}