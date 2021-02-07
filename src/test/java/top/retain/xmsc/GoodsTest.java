package top.retain.xmsc;

import org.junit.jupiter.api.Test;
import top.retain.xmsc.dao.IGoodsDao;
import top.retain.xmsc.dao.impl.GoodsDaoImpl;
import top.retain.xmsc.dto.Goods;
import top.retain.xmsc.dto.Type;

/**
 * @author Retain
 * @date 2021/1/15 9:22
 */
public class GoodsTest {
    IGoodsDao goodsDao=new GoodsDaoImpl();

    @Test
    void testAdd(){
        Type type = Type.builder().id(2).build();
//        Goods goods = Goods.builder().goodsName("小米10").goodsNum(1).goodsPrice(2999d).goodsDesc("6666").goodsDetail("小米10666").typeId(.build();
//        System.out.println(goodsDao.addGoods(goods));
    }

    @Test
    void testModify(){
        Type type = Type.builder().id(3).build();
//        Goods goods = Goods.builder().goodsId(1).goodsName("小米11").goodsNum(1).goodsPrice(2999d).goodsDesc("6666").goodsDetail("小米10666").type(type).build();
//        System.out.println(goodsDao.modifyGoods(goods));
    }

    @Test
    void testDelete(){
        System.out.println(goodsDao.deleteGoods(1));
    }

    @Test
    void testGetGoodsPaged(){
        System.out.println(goodsDao.getAllGoods(1,5));
    }

    @Test
    void testGetGoodsById(){
        System.out.println(goodsDao.getGoodsById(2));
    }
    @Test
    void testGetGoodsByType(){
        System.out.println(goodsDao.getGoodsByType(1,3,2));
        System.out.println(goodsDao.getCount(2));
    }
}
