package com.distribution.common.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.distribution.common.Dto.vfGoods.reqQueryGoods;
import com.distribution.common.Pojo.Vo.VFGoodsDealerVo;
import com.distribution.common.Pojo.Vo.vfGoodVo;
import com.distribution.common.Pojo.vfGoods;
import com.distribution.common.Pojo.vfGoodstype;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* @author admin
* @description 针对表【vf_goods】的数据库操作Mapper
* @createDate 2022-11-30 16:49:45
* @Entity com.distribution.common.Pojo.vfGoods
*/
@Repository
public interface vfGoodsMapper extends BaseMapper<vfGoods> {
    List<vfGoodVo> getgoodsinfo(Page page,@Param("reqQueryGoods") reqQueryGoods reqQueryGoods);

    List<VFGoodsDealerVo> getgoodsinfoByDealer(Page page,@Param("reqQueryGoods") reqQueryGoods reqQueryGoods);

    List<vfGoodVo> getgoodsinfoBygoodsID(@Param("ids") String ids);
}




