package com.distribution.common.Dto.vfUser;

import com.distribution.common.Dto.DtoQuery.requestQueryDtoBase;
import lombok.Data;

@Data
public class reqQueryUserDto extends requestQueryDtoBase {
    String username;
    String companyname;
    int status;
}
