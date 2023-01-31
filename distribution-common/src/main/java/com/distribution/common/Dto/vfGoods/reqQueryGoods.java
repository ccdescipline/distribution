package com.distribution.common.Dto.vfGoods;

import com.distribution.common.Dto.DtoQuery.requestQueryDtoBase;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

@Data
public class reqQueryGoods extends requestQueryDtoBase {

    String goodsname;


    BigDecimal startprice;

    BigDecimal endprice;

    Integer status;

    String goodstype;

    /**
     * 后端修改的值
     */
    @Null
    String userid;


    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime starttime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime endtime;
}
