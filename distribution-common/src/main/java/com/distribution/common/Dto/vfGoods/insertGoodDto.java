package com.distribution.common.Dto.vfGoods;

import com.distribution.common.Pojo.vfGoods;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class insertGoodDto extends vfGoods {
//    String goodsname;
//    String goodstype;
//    String goodsprice;
//    String goodspackage;
    @Min(value = 0,message = "数量必须大于0")
    int count;

    @NotNull(message = "商品描述图片不能为空")
    String showimg;
    @NotNull(message = "商品详情图片不能为空")
    List<String> detailimgs;
}
