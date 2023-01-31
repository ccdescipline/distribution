package org.example;

import com.distribution.system.Service.VFRoleService;
import com.distribution.system.Service.impl.VFRoleServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestLogin {
    @Autowired
    private VFRoleService vfRoleService = new VFRoleServiceImpl();

    @Test
    public void testrole(){
        System.out.println(vfRoleService.getRoleinfoByRegister());

    }


}
