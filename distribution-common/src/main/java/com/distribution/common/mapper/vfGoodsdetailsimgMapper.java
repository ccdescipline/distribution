package com.distribution.common.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.distribution.common.Pojo.Vo.vfGoodsDetailsImgVo;
import com.distribution.common.Pojo.vfGoodsdetailsimg;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* @author admin
* @description 针对表【vf_goodsdetailsimg】的数据库操作Mapper
* @createDate 2022-12-08 17:24:56
* @Entity com.distribution.common.Pojo.vfGoodsdetailsimg
*/
@Repository
public interface vfGoodsdetailsimgMapper extends BaseMapper<vfGoodsdetailsimg> {

    /**
     * 根据商品信息主键查询商品描述图片
     * @param goodsinfoid
     * @return
     */
    List<vfGoodsDetailsImgVo> getGoodsdetailsimginfo(@Param("goodsinfoid") String goodsinfoid);
}




