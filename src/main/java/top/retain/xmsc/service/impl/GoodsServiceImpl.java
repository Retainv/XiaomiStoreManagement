package top.retain.xmsc.service.impl;

import top.retain.xmsc.dao.IGoodsDao;
import top.retain.xmsc.dao.impl.GoodsDaoImpl;
import top.retain.xmsc.dto.Goods;
import top.retain.xmsc.service.IGoodsService;

import java.util.List;

/**
 * @author Retain
 * @date 2021/1/15 9:52
 */
public class GoodsServiceImpl implements IGoodsService {
    IGoodsDao goodsDao=new GoodsDaoImpl();
    @Override
    public int addGoods(Goods goods) {
        return goodsDao.addGoods(goods);
    }

    @Override
    public int modifyGoods(Goods type) {
        return goodsDao.modifyGoods(type);
    }

    @Override
    public int deleteGoods(Integer id) {
        return goodsDao.deleteGoods(id);
    }

    @Override
    public List<Goods> getAllGoods(int currentPage, int size) {
        return goodsDao.getAllGoods(currentPage, size);
    }

    @Override
    public Goods getGoodsById(Integer id) {
        return goodsDao.getGoodsById(id);
    }

    @Override
    public int getCount(int typeId) {
        return goodsDao.getCount(typeId);
    }

    @Override
    public List<Goods> getGoodsByType(int currentPage, int size, int typeId) {
        return goodsDao.getGoodsByType(currentPage, size, typeId);
    }
}
