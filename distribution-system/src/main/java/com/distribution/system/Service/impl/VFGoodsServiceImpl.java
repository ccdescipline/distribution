package com.distribution.system.Service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.toolkit.BeanUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.distribution.common.Dto.vfGoods.*;
import com.distribution.common.Pojo.Vo.VFGoodsDealerVo;
import com.distribution.common.Pojo.Vo.vfGoodVo;
import com.distribution.common.Pojo.Vo.vfGoodsDetailsImgVo;
import com.distribution.common.Pojo.Vo.vfGoodsinfoVo;
import com.distribution.common.Pojo.vfGoods;
import com.distribution.common.Pojo.vfGoodsdetailsimg;
import com.distribution.common.Pojo.vfGoodsinfo;
import com.distribution.common.Utils.MinioUtils;
import com.distribution.common.mapper.vfGoodsMapper;
import com.distribution.common.mapper.vfGoodsdetailsimgMapper;
import com.distribution.common.mapper.vfGoodsinfoMapper;
import com.distribution.common.responsResult;
import com.distribution.system.Service.VFGoodsService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Log4j
@Service
public class VFGoodsServiceImpl extends ServiceImpl<vfGoodsMapper, vfGoods> implements VFGoodsService {
    @Autowired
    private vfGoodsMapper vfGoodsMapper;

    @Autowired
    private vfGoodsinfoMapper vfGoodsinfoMapper;

    @Autowired
    private vfGoodsdetailsimgMapper vfGoodsdetailsimgMapper;

    @Autowired
    private MinioUtils minioUtils;

    @Override
    public responsResult getgoodinfo(reqQueryGoods reqQueryGoods) {



        Page page = Page.of(reqQueryGoods.getIndex(), reqQueryGoods.getPage());
        List<vfGoodVo> getgoodsinfo = vfGoodsMapper.getgoodsinfo(page,
                reqQueryGoods);

        return responsResult.success(resQueryGoods.setdata(getgoodsinfo,page.getPages()));

    }

    @Override
    public responsResult updateinfo(updateGoodsDto updateGoodsDto) {
        vfGoods one = getOne(Wrappers.<vfGoods>lambdaQuery()
                .eq(vfGoods::getGoodsid, updateGoodsDto.getGoodsid()));
        if(one==null){
            return responsResult.error("??????????????????");
        }

        one.setGoodsname(updateGoodsDto.getGoodsname());
        one.setGoodstype(updateGoodsDto.getGoodstype());
        one.setStatus(updateGoodsDto.getStatus());
        one.setGoodstype(updateGoodsDto.getGoodstype());
        one.setGoodspackage(updateGoodsDto.getGoodspackage());
        one.setCount(updateGoodsDto.getCount());

        //????????????
        one.setUpdatetime(LocalDateTime.now());

        boolean b = updateById(one);
        if(b){
            return responsResult.success("????????????");
        }else {
            return responsResult.error("????????????");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public responsResult insertinfo(insertGoodDto updateGoodsDto) {
        //??????uuid
        updateGoodsDto.setGoodsid(UUID.randomUUID().toString());
        //????????????
        updateGoodsDto.setCreatetime(LocalDateTime.now());
        updateGoodsDto.setUpdatetime(LocalDateTime.now());

        //??????????????????
        updateGoodsDto.setStatus(1);

        //??????????????????
        vfGoodsinfo goodsinfo = new vfGoodsinfo();
        goodsinfo.setGoodsinfo(UUID.randomUUID().toString());
        goodsinfo.setGoodsshowimg(updateGoodsDto.getShowimg());

        if(vfGoodsinfoMapper.insert(goodsinfo)!=1){
            throw new RuntimeException("??????vfGoodsinfo ??????");
        }

        //????????????
        updateGoodsDto.setGoodsinfoid(goodsinfo.getGoodsinfo());

        int index = 0;
        for (String i: updateGoodsDto.getDetailimgs()){
            vfGoodsdetailsimg vfGoodsdetailsimg = new vfGoodsdetailsimg();
            vfGoodsdetailsimg.setGoodsdetailsimgid(UUID.randomUUID().toString());
            vfGoodsdetailsimg.setGoodsinfoid(goodsinfo.getGoodsinfo());
            vfGoodsdetailsimg.setIndex(index);
            vfGoodsdetailsimg.setGoodsdetailsimg(i);

            if(vfGoodsdetailsimgMapper.insert(vfGoodsdetailsimg)!=1){

                throw new RuntimeException("??????vfGoodsdetailsimg ??????");
            }

            index++;
        }

        boolean save = save(updateGoodsDto);
        if(save){
            return responsResult.success("????????????");
        }else {
            return responsResult.error("????????????");
        }

    }

    @Override
    public responsResult updateinfobysupplier(supplierUpdateGoodsDto updateGoodsDto) {
        vfGoods one = getOne(Wrappers.<vfGoods>lambdaQuery()
                .eq(vfGoods::getGoodsid, updateGoodsDto.getGoodsid()));
        if(one==null){
            return responsResult.error("??????????????????");
        }

        //???????????????????????????
        if(!one.getCreateuserid().equals(updateGoodsDto.getUserid())){
            return responsResult.error("?????????????????????");
        }

        //????????????
        return this.updateinfo(updateGoodsDto);
    }

    @Override
    public responsResult getgoodinfoByDealer(reqQueryGoods reqQueryGoods) {
        Page page = Page.of(reqQueryGoods.getIndex(), reqQueryGoods.getPage());
        List<VFGoodsDealerVo> getgoodsinfo = vfGoodsMapper.getgoodsinfoByDealer(page,
                reqQueryGoods);

        AtomicInteger index = new AtomicInteger();
        //???????????????????????????????????????
        List<VFGoodsDealerVo> collect = getgoodsinfo.stream().map((x) -> {
            vfGoodsinfoVo goodsinfo = x.getGoodsinfo();
            vfGoodsinfoVo vfGoodsinfoVo = new vfGoodsinfoVo();
            BeanUtil.copyProperties(goodsinfo,vfGoodsinfoVo);
            try {
                //???????????????
                vfGoodsinfoVo.setGoodsshowimg(minioUtils.getObjectUrlByName(goodsinfo.getGoodsshowimg()));
                index.addAndGet(1);
                //???????????????
                List<vfGoodsDetailsImgVo> GoodsDetailsImgcollect = goodsinfo.getVfGoodsDetailsImgs().stream().map(vfGoodsDetailsImgVo -> {

                    vfGoodsDetailsImgVo newvfGoodsDetailsImgVo = new vfGoodsDetailsImgVo();
                    BeanUtil.copyProperties(vfGoodsDetailsImgVo,newvfGoodsDetailsImgVo);
                    try {
                        newvfGoodsDetailsImgVo.setGoodsdetailsimg(minioUtils.getObjectUrlByName(vfGoodsDetailsImgVo.getGoodsdetailsimg()));
                    } catch (Exception e) {
                        newvfGoodsDetailsImgVo.setGoodsdetailsimg("#");
                    }

                    //newvfGoodsDetailsImgVo.setIndex(vfGoodsDetailsImgVo.getIndex());

                    return newvfGoodsDetailsImgVo;
                }).collect(Collectors.toList());

                vfGoodsinfoVo.setVfGoodsDetailsImgs(GoodsDetailsImgcollect);

                //fGoodsinfoVo.setGoodsinfo(goodsinfo.getGoodsinfo());

            } catch (Exception e) {
                vfGoodsinfoVo.setGoodsshowimg("#");
            }


            x.setGoodsinfo(vfGoodsinfoVo);
            return x;
        }).collect(Collectors.toList());

        return responsResult.success(resQueryGoods.setdata(collect,page.getPages()));
    }

    @Override
    public responsResult uploadImg(MultipartFile[] file) throws IOException {
        List<String> list = new ArrayList<>();
        for (MultipartFile i:file) {
            String key = UUID.randomUUID().toString()+".jpg";
            minioUtils.saveImgInMinio(i.getInputStream(),key);
            list.add(key);
        }

       return responsResult.success(list);
    }


}
