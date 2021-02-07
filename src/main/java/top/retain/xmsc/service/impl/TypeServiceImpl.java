package top.retain.xmsc.service.impl;

import top.retain.xmsc.dao.ITypeDao;
import top.retain.xmsc.dao.impl.TypeDaoImpl;
import top.retain.xmsc.dto.Type;
import top.retain.xmsc.service.ITypeService;

import java.util.List;

/**
 * @author Retain
 * @date 2021/1/12 19:14
 */
public class TypeServiceImpl implements ITypeService {
    ITypeDao typeDao=new TypeDaoImpl();
    @Override
    public int addType(Type type) {
        return typeDao.addType(type);
    }

    @Override
    public int modifyType(Type type) {
        return typeDao.modifyType(type);
    }

    @Override
    public int deleteType(Integer id) {
        return typeDao.deleteType(id);
    }

    @Override
    public List<Type> getAllTypes(int currentPage,int size) {
        return typeDao.getAllTypes(currentPage,size);
    }

    @Override
    public Type getTypeById(Integer id) {
        return typeDao.getTypeById(id);
    }

    @Override
    public int getCount() {
        return typeDao.getCount();
    }
}
