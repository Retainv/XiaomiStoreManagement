package top.retain.xmsc;

import org.junit.jupiter.api.Test;
import top.retain.xmsc.dto.Manager;
import top.retain.xmsc.service.IManagerService;
import top.retain.xmsc.service.impl.ManagerServiceImpl;

/**
 * @author Retain
 * @date 2021/1/12 19:15
 */

public class ManagerTest {

    IManagerService managerService=new ManagerServiceImpl();
    @Test
    void testAdd(){
    }

    @Test
    void testLogin(){
        Manager build = Manager.builder().account("123").pwd("123").build();
        System.out.println(managerService.login(build));
    }
}
