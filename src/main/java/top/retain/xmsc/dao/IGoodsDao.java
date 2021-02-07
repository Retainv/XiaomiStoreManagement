package top.retain.xmsc.dao;

import top.retain.xmsc.dto.Goods;
import top.retain.xmsc.dto.Goods;

import java.util.List;

/**
 * @author Retain
 * @date 2021/1/15 9:11
 */
public interface IGoodsDao {
    /**
     * 添加商品
     * @param
     * @return
     */
    public int addGoods(Goods goods);

    /**
     * 修改商品
     * @param type
     * @return
     */
    int modifyGoods(Goods type);

    /**
     * 删除商品类别
     * @param id
     * @return
     */
    int deleteGoods(Integer id);

    /**
     * 分页获取所有商品
     * @return
     */
    List<Goods> getAllGoods(int currentPage, int size);

    /**
     * 根据id获取类别
     * @param id
     * @return
     */
    Goods getGoodsById(Integer id);

    /**
     * 获取数据总条数 typeId=-1:查询所有
     * @return
     */
    int getCount(int typeId);

    /**
     * 根据类型查询
     * @return
     */
    List<Goods> getGoodsByType(int currentPage, int size,int typeId);


}
