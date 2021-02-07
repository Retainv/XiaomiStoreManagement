package top.retain.xmsc.dao.impl;

import top.retain.xmsc.dao.IManagerDao;
import top.retain.xmsc.dto.Manager;
import top.retain.xmsc.dto.Manager;
import top.retain.xmsc.util.DBHelper;
import top.retain.xmsc.util.JDBCUtils;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Retain
 * @date 2021/1/12 18:55
 */
public class ManagerDaoImpl implements IManagerDao {
    @Override
    public boolean login(Manager manager) {
        String account= manager.getAccount();
        String password = manager.getPwd();
        try {
            String sql="select * from tb_manager where mgr_account=? and mgr_pwd=?";
            String[] params={account, password};
            ResultSet rs = DBHelper.executeQuery(sql, params);

            if (rs.next()) {
                JDBCUtils.close(null,DBHelper.getPreparedStatement(),rs);
                return true;
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
