package com.distribution.web.controller;

import com.distribution.common.responsResult;
import com.distribution.common.Dto.vfGoodstype.insertGoodstype;
import com.distribution.common.Dto.vfGoodstype.updateGoodstype;
import com.distribution.system.Service.VFGoodstypeService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Log4j
@RestController
@RequestMapping("/goodstype")
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class VFGoodstypeController {

    @Autowired
    private VFGoodstypeService vfGoodstypeService;

    @GetMapping()
    public responsResult getinfo(){
        return  responsResult.success(vfGoodstypeService.list());
    }

    @PostMapping()
    public responsResult insertinfo(@RequestBody insertGoodstype insertGoodstype){
        return vfGoodstypeService.insertGoodstype(insertGoodstype);
    }
    @PutMapping()
    public responsResult update(@RequestBody updateGoodstype updateGoodstype){
        return vfGoodstypeService.updateGoodstype(updateGoodstype);
    }

    /**
     * 获取激活状态的商品品名
     * @return
     */
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_SUPPLIER','ROLE_DELAER')")
    @GetMapping("/active")
    public  responsResult getactiveinfo(){
        return vfGoodstypeService.getactiveinfo();
    }
}
