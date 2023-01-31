package com.distribution.common.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.distribution.common.Pojo.Vo.vfGoodsinfoVo;
import com.distribution.common.Pojo.vfGoodsinfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
* @author admin
* @description 针对表【vf_goodsinfo】的数据库操作Mapper
* @createDate 2022-12-08 17:24:33
* @Entity com.distribution.common.Pojo.vfGoodsinfo
*/
@Repository
public interface vfGoodsinfoMapper extends BaseMapper<vfGoodsinfo> {

    vfGoodsinfoVo getGoodsOneVo(@Param("goodsinfoid") String goodsinfoid );


}




