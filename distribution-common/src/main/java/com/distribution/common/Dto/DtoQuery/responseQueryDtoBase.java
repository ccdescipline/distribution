package com.distribution.common.Dto.DtoQuery;

import lombok.Data;

@Data
public class responseQueryDtoBase<T> {

    /**
     * 返回的数据
     */
    private T datalist;
    /**
     * 总行数
     */
    private long rowcount;

    public static <T> responseQueryDtoBase<T> setdata(T data,long count){
        responseQueryDtoBase responseQueryDtoBase = new responseQueryDtoBase();
        responseQueryDtoBase.setDatalist(data);
        responseQueryDtoBase.setRowcount(count);

        return responseQueryDtoBase;

    }
}
