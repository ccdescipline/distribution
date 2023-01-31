package com.distribution.system.Service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.distribution.common.Dto.vfGoods.*;
import com.distribution.common.Dto.vfStock.goodsinStockDto;
import com.distribution.common.Pojo.vfGoods;
import com.distribution.common.Pojo.vfUser;
import com.distribution.common.responsResult;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface VFGoodsService extends IService<vfGoods> {
    /**
     * 查询商品信息
     * @param reqQueryGoods
     * @return
     */
    responsResult getgoodinfo(reqQueryGoods reqQueryGoods);

    responsResult updateinfo(updateGoodsDto updateGoodsDto);

    responsResult insertinfo(insertGoodDto updateGoodsDto);

    /**
     * 供应商修改商品信息
     * @param updateGoodsDto
     * @return
     */
    responsResult updateinfobysupplier(supplierUpdateGoodsDto updateGoodsDto);

    /**
     * 经销商查看商品信息
     * @param reqQueryGoods
     * @return
     */
    responsResult getgoodinfoByDealer(reqQueryGoods reqQueryGoods);


    /**
     * 图片上传
     * @param file
     * @return
     */
    responsResult uploadImg(MultipartFile[] file) throws IOException;
}
