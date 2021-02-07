package top.retain.xmsc.dao.impl;

import lombok.val;
import top.retain.xmsc.dao.IGoodsDao;
import top.retain.xmsc.dto.Goods;
import top.retain.xmsc.dto.Goods;
import top.retain.xmsc.dto.Type;
import top.retain.xmsc.util.DBHelper;
import top.retain.xmsc.util.JDBCUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Retain
 * @date 2021/1/15 9:13
 */
public class GoodsDaoImpl implements IGoodsDao {
    @Override
    public int addGoods(Goods goods) {
        try {
            String sql="insert into tb_goods(goods_name,goods_price,goods_num,goods_desc,goods_detail,goods_tid,goods_img) values(?,?,?,?,?,?,?)";
            Object[] params={goods.getGoodsName(),goods.getGoodsPrice(),goods.getGoodsNum(),goods.getGoodsDesc(),goods.getGoodsDetail(),goods.getTypeId(),goods.getGoodsImg()};

            int rs = DBHelper.executeNonQuery(sql, params);

            if (rs==1) {
                JDBCUtils.close(null, DBHelper.getPreparedStatement(),null);
                return 1;
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int modifyGoods(Goods goods) {
        try {
            String sql="update tb_goods set goods_name=?,goods_price=?,goods_num=?,goods_desc=?,goods_detail=?,goods_tid=?,goods_img=? where goods_id=?";
            Object[] params={goods.getGoodsName(),goods.getGoodsPrice(),goods.getGoodsNum(),goods.getGoodsDesc(),goods.getGoodsDetail(),goods.getTypeId(),goods.getGoodsImg(),goods.getGoodsId()};

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
    public int deleteGoods(Integer id) {
        try {
            String sql="delete from tb_goods where goods_id=?";
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
    public List<Goods> getAllGoods(int currentPage, int size) {
        ArrayList<Goods> goods = new ArrayList<>();
        try {
            String sql="select * from tb_goods inner join tb_type on tb_goods.goods_tid=tb_type.type_id limit ?,?";
            int start=(currentPage-1)*size;
            Object[] params={start,size};
            ResultSet rs = DBHelper.executeQuery(sql,params);
            while (rs.next()){
                Goods good = getFields(rs);
                goods.add(good);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return goods;
    }

    @Override
    public Goods getGoodsById(Integer id) {
        Goods goods = new Goods();
        try {
            String sql="select * from tb_goods inner join tb_type on tb_goods.goods_tid=tb_type.type_id where goods_id=?";
            Object[] params={id};
            ResultSet rs = DBHelper.executeQuery(sql,params);
            if (rs.next()){
                goods = getFields(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return goods;
    }

    public Goods getFields(ResultSet rs) throws SQLException{
        int id = rs.getInt("goods_id");
        String name = rs.getString("goods_name");
        double price = rs.getFloat("goods_price");
        int goodsNum = rs.getInt("goods_num");
        String desc = rs.getString("goods_desc");
        String detail = rs.getString("goods_detail");
        String goodsImg = rs.getString("goods_img");
        int tid = rs.getInt("goods_tid");
        Goods good = Goods.builder().goodsId(id).goodsName(name).goodsPrice(price).goodsNum(goodsNum)
                .goodsDesc(desc).goodsDetail(detail).typeId(tid).goodsImg(goodsImg).build();
        return good;
    }

    @Override
    public int getCount(int typeId) {
        String sql="select count(0) from tb_goods";
        Object[] params={};
        //如果是根据类别查询
        if (typeId != -1){
            sql="select count(0) from tb_goods where goods_tid=?";
            params=new Object[1];
            params[0]=typeId;
        }
        try {
            ResultSet rs = DBHelper.executeQuery(sql, params);

            if (rs.next()){
                return rs.getInt("count(0)");
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<Goods> getGoodsByType(int currentPage, int size, int typeId) {
        ArrayList<Goods> goods = new ArrayList<>();
        try {
            String sql="select * from tb_goods where goods_tid=? limit ?,?";
            int start=(currentPage-1)*size;
            Object[] params={typeId,start,size};
            ResultSet rs = DBHelper.executeQuery(sql,params);
            while (rs.next()){
                Goods good = getFields(rs);
                goods.add(good);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return goods;
    }

}
