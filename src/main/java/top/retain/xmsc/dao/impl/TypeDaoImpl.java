package top.retain.xmsc.dao.impl;

import top.retain.xmsc.dao.ITypeDao;
import top.retain.xmsc.dto.Type;
import top.retain.xmsc.util.DBHelper;
import top.retain.xmsc.util.JDBCUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Retain
 * @date 2021/1/12 19:11
 */
public class TypeDaoImpl implements ITypeDao {
    @Override
    public int addType(Type type) {
        try {
            String sql="insert into tb_type(type_name,type_desc) values(?,?)";
            Object[] params={type.getName(),type.getDescription()};

            int rs = DBHelper.executeNonQuery(sql, params);

            if (rs==1) {
                JDBCUtils.close(null,DBHelper.getPreparedStatement(),null);
                return 1;
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int modifyType(Type type) {
        try {
            String sql="update tb_type set type_name=?,type_desc=? where type_id=?";
            Object[] params={type.getName(),type.getDescription(),type.getId()};

            int rs = DBHelper.executeNonQuery(sql, params);

            if (rs==1) {
                JDBCUtils.close(null,DBHelper.getPreparedStatement(),null);
                return 1;
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int deleteType(Integer id) {
        try {
            String sql="delete from tb_type where type_id=?";
            Object[] params={id};

            int rs = DBHelper.executeNonQuery(sql, params);

            if (rs==1) {
                JDBCUtils.close(null,DBHelper.getPreparedStatement(),null);
                return 1;
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<Type> getAllTypes(int currentPage,int size) {
        ArrayList<Type> types = new ArrayList<>();
        try {
            String sql="select * from tb_type limit ?,?";
            int start=(currentPage-1)*size;
            Object[] params={start,size};
            ResultSet rs = DBHelper.executeQuery(sql,params);
            while (rs.next()){
                int id = rs.getInt("type_id");
                String name = rs.getString("type_name");
                String desc = rs.getString("type_desc");
                Type type = Type.builder().id(id).name(name).description(desc).build();
                types.add(type);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return types;
    }

    @Override
    public Type getTypeById(Integer id) {
        Type type = new Type();
        try {
            String sql="select * from tb_type where type_id=?";
            Object[] params={id};

            ResultSet rs = DBHelper.executeQuery(sql, params);

            if (rs.next()){
                int typeId = rs.getInt("type_id");
                String name = rs.getString("type_name");
                String desc = rs.getString("type_desc");
                type = Type.builder().id(typeId).name(name).description(desc).build();
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return type;
    }

    @Override
    public int getCount() {
        try {
            String sql="select count(0) from tb_type";
            Object[] params={};

            ResultSet rs = DBHelper.executeQuery(sql, params);

            if (rs.next()){
                return rs.getInt("count(0)");
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
