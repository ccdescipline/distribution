package com.distribution.system.Service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.distribution.common.Dto.vfReceiveadress.insertReceiveaddressDto;
import com.distribution.common.Dto.vfReceiveadress.removeReceiveaddressDto;
import com.distribution.common.Dto.vfReceiveadress.updateReceiveaddressDto;
import com.distribution.common.Pojo.vfReceiveaddress;
import com.distribution.common.Pojo.vfUser;
import com.distribution.common.responsResult;

public interface VFReceiveAdressService extends IService<vfReceiveaddress> {
    responsResult getcurrentReceiveddress(vfUser vfUser);

    responsResult insertReceiveddress(vfUser vfUser, insertReceiveaddressDto insertReceiveaddressDto) throws Exception;

    responsResult updateReceiveddress(vfUser vfUser, updateReceiveaddressDto updateReceiveaddressDto) throws Exception;

    responsResult removeReceiveddress(vfUser vfUser, removeReceiveaddressDto removeReceiveaddressDto) throws Exception;
}
