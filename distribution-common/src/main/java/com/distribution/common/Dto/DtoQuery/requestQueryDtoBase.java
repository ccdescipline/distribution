package com.distribution.common.Dto.DtoQuery;

import lombok.Data;

import javax.validation.constraints.Min;

@Data
public class requestQueryDtoBase {
    /**
     * 页数
     */
    @Min(value = 1,message = "页数最小1")
    private int index;
    /**
     * 每行显示的数
     */
    private int page;
}
