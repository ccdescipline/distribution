package com.distribution.common.Dto.vfReceiveadress;

import com.distribution.common.Pojo.vfReceiveaddress;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(value = {"createtime", "updatetime","userid","status"})
public class queryReceiveaddressDto extends vfReceiveaddress {
}
