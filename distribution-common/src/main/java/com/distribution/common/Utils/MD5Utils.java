package com.distribution.common.Utils;

import org.apache.commons.codec.digest.DigestUtils;

public class MD5Utils {
    /**
     * MD5加密
     * @param pwd
     * @param salt
     * @return
     */
    public static String md5Encryption(String pwd,String salt){
        String source = pwd + salt;

        for (int i = 0;i<1024;i++){
            source = DigestUtils.md5Hex(source);
        }

        return source;
    }
}
