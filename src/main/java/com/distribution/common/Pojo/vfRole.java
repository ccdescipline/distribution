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
 * @TableName vf_role
 */
@TableName(value ="vf_role")
@Data
public class vfRole implements Serializable {
    /**
     * 角色主键
     */
    @TableId
    private String roleid;

    /**
     * 角色名字
     */
    private String rolename;

    /**
     * 状态（1 启用 0 禁用）
     */
    private Integer rolestatus;

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