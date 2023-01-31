package com.distribution.common.Pojo.Vo;

import com.distribution.common.Pojo.vfGoods;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data

@JsonIgnoreProperties(value = {"createtime", "updatetime","createuserid","goodsinfoid"})

public class VFGoodsDealerVo extends vfGoods {
    /**
     * 品名
     */
    String goodstypename;

    /**
     * 供应商名称
     */
    String companyname;

    /**
     * 数量
     */
    int count;


    vfGoodsinfoVo goodsinfo;
}
