package com.distribution.common.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.distribution.common.Dto.vfReceiveadress.queryReceiveaddressDto;
import com.distribution.common.Pojo.vfReceiveaddress;
import com.distribution.common.Pojo.vfUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* @author admin
* @description 针对表【vf_receiveaddress】的数据库操作Mapper
* @createDate 2022-12-14 22:02:05
* @Entity com.distribution.common.Pojo.vfReceiveaddress
*/
@Repository
public interface vfReceiveaddressMapper extends BaseMapper<vfReceiveaddress> {

    List<queryReceiveaddressDto> getcurrentReceiveaddressinfo(@Param("vfUser") vfUser vfUser);
}




