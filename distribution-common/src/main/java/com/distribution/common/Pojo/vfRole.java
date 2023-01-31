package com.distribution.common.Pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName(value = "vf_role")
@Data
public class vfRole {
  @TableId
  @TableField( value="RoleID")
  private String roleid;

  @TableField(value="RoleName")
  private String roleName;
  @TableField(value="RoleStatus")
  private long roleStatus;
  @TableField(value="CreateTime")
  private java.sql.Timestamp createTime;
  @TableField(value="UpdateTime")
  private java.sql.Timestamp updateTime;



  public String getRoleName() {
    return roleName;
  }

  public void setRoleName(String roleName) {
    this.roleName = roleName;
  }


  public long getRoleStatus() {
    return roleStatus;
  }

  public void setRoleStatus(long roleStatus) {
    this.roleStatus = roleStatus;
  }


  public java.sql.Timestamp getCreateTime() {
    return createTime;
  }

  public void setCreateTime(java.sql.Timestamp createTime) {
    this.createTime = createTime;
  }


  public java.sql.Timestamp getUpdateTime() {
    return updateTime;
  }

  public void setUpdateTime(java.sql.Timestamp updateTime) {
    this.updateTime = updateTime;
  }

}
