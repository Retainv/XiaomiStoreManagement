package top.retain.xmsc.dao;

import top.retain.xmsc.dto.Type;

import java.util.List;

/**
 * @author Retain
 * @date 2021/1/12 19:10
 */
public interface ITypeDao {

    /**
     * 添加商品类别
     * @param type
     * @return
     */
    public int addType(Type type);

    /**
     * 修改商品类别
     * @param type
     * @return
     */
    int modifyType(Type type);

    /**
     * 删除商品类别
     * @param id
     * @return
     */
    int deleteType(Integer id);

    /**
     * 分页获取所有类别
     * @return
     */
    List<Type> getAllTypes(int currentPage,int size);

    /**
     * 根据id获取类别
     * @param id
     * @return
     */
    Type getTypeById(Integer id);

    /**
     * 获取数据总条数
     * @param
     * @return
     */
    int getCount();
}
