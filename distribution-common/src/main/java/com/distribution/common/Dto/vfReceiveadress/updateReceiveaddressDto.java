package com.distribution.common.Dto.vfReceiveadress;

import com.distribution.common.Pojo.vfReceiveaddress;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Null;
import java.util.Date;

@Data
public class updateReceiveaddressDto extends vfReceiveaddress {

    /**
     * 创建时间
     */
    @NotEmpty(message = "参数非法")
    private Date createtime;

    /**
     * 修改时间
     */
    @NotEmpty(message = "参数非法")
    private Date updatetime;

    /**
     * 所属ID
     */
    @NotEmpty(message = "参数非法")
    private String userid;

    /**
     * 状态（0 弃用 1启用）
     */
    @NotEmpty(message = "参数非法")
    private Integer status;
}
