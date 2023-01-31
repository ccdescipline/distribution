package org.example;

import com.distribution.common.Utils.MD5Utils;
import org.junit.Test;


public class Testmd5 {
    /**
     * 生成密码
     */
    @Test
    public void testmd5(){
        System.out.println(
                MD5Utils.md5Encryption("123456","4a98e52f-68c9-11ed-813f-3024323e00de")
        );
    }


}
