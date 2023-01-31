package com.distribution.web.controller;

import com.distribution.common.responsResult;
import com.distribution.common.Dto.vfGoods.insertGoodDto;
import com.distribution.common.Dto.vfGoods.reqQueryGoods;
import com.distribution.common.Dto.vfGoods.updateGoodsDto;
import com.distribution.system.Service.VFGoodsService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Log4j
@RestController
@RequestMapping("/goods")
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class VFGoodsController {
    @Autowired
    private VFGoodsService vfGoodsService;

    @GetMapping()
    public responsResult getGoodsinfo(reqQueryGoods reqQueryGoods){

        reqQueryGoods.setUserid(null);
        return vfGoodsService.getgoodinfo(reqQueryGoods);
    }

    @PutMapping()
    public  responsResult updateinfo(@RequestBody updateGoodsDto updateGoodsDto){
        return vfGoodsService.updateinfo(updateGoodsDto);
    }

    @PostMapping()
    public  responsResult insertinfo(@RequestBody insertGoodDto insertGoodDto){
        return vfGoodsService.insertinfo(insertGoodDto);
    }

}
