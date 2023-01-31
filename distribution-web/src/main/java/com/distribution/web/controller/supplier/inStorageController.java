package com.distribution.web.controller.supplier;

import com.distribution.common.Dto.vfGoods.insertGoodDto;
import com.distribution.common.Dto.vfGoods.reqQueryGoods;
import com.distribution.common.Dto.vfGoods.supplierUpdateGoodsDto;
import com.distribution.common.Dto.vfGoods.updateGoodsDto;
import com.distribution.common.Dto.vfStock.goodsinStockDto;
import com.distribution.common.Pojo.vfUser;
import com.distribution.common.responsResult;
import com.distribution.system.Service.VFGoodsService;
import com.distribution.system.Service.impl.VFStockServiceImpl;
import com.distribution.web.Utils.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * 供应商入库
 */

@RestController
@RequestMapping("/inStorage")
@PreAuthorize("hasAuthority('ROLE_SUPPLIER')")
public class inStorageController {

    @Autowired
    private VFGoodsService vfGoodsService;

    @GetMapping("/getgoodsinfo")
    public responsResult getcurrentgoodsInfd(reqQueryGoods reqQueryGoods){
        vfUser vfUser = SessionUtils.GetCurrentUser();

        reqQueryGoods.setUserid(vfUser.getUserid());

        return vfGoodsService.getgoodinfo(reqQueryGoods);
    }

    /**
     * 供应商上架商品
     */
    @PostMapping()
    public  responsResult insertinfo(@RequestBody @Validated insertGoodDto insertGoodDto){

        vfUser vfUser = SessionUtils.GetCurrentUser();
        insertGoodDto.setCreateuserid(vfUser.getUserid());
        return vfGoodsService.insertinfo(insertGoodDto);
    }

    /**
     * 修改商品信息
     * @param updateGoodsDto
     * @return
     */
    @PutMapping()
    public  responsResult updateinfo(@RequestBody @Validated supplierUpdateGoodsDto updateGoodsDto){
        //赋值用户id
        updateGoodsDto.setUserid(SessionUtils.GetCurrentUser().getUserid());

        return vfGoodsService.updateinfobysupplier(updateGoodsDto);
    }

    @PostMapping("/uploadImg")
    public responsResult uploadImg(@RequestParam("image") MultipartFile[] file) throws IOException {
        return vfGoodsService.uploadImg(file);
    }
}
