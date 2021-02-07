package top.retain.xmsc;

import org.junit.jupiter.api.Test;
import top.retain.xmsc.dto.Type;
import top.retain.xmsc.service.ITypeService;
import top.retain.xmsc.service.impl.TypeServiceImpl;

/**
 * @author Retain
 * @date 2021/1/13 19:12
 */
public class TypeTest {
    ITypeService typeService=new TypeServiceImpl();

    @Test
    void testAdd(){
        Type build = Type.builder().name("家电").description("家具家电在线销售").build();
        System.out.println(typeService.addType(build));
    }

    @Test
    void testGetAll(){
//        System.out.println(typeService.getAllTypes());
    }

    @Test
    void testGetTypeById(){
        System.out.println(typeService.getTypeById(1));
    }

    @Test
    void testModify(){
        Type build = Type.builder().name("手机").description("小米11").id(1).build();
        System.out.println(typeService.modifyType(build));
        System.out.println(typeService.getTypeById(1));
    }

    @Test
    void testDelete(){
        System.out.println(typeService.deleteType(1));
        System.out.println(typeService.getTypeById(1));
    }
}
