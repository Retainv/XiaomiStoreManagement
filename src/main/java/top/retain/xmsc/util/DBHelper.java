package top.retain.xmsc.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 数据库操作工具类,封装了JDBCUtils
 */
public class DBHelper {

    private static Connection conn=null;
    private static PreparedStatement ps=null;
    private static ResultSet rs=null;

    public static ResultSet getResultSet() throws SQLException {
        return rs;
    }
    public static PreparedStatement getPreparedStatement() throws SQLException {
        return ps;
    }
    public static Connection getConnection() throws SQLException {
        return JDBCUtils.getConnection();
    }

    public static ResultSet executeQuery(String sql,Object[] params) throws SQLException {
        ResultSet rs = null;
        conn = JDBCUtils.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        if (params!=null){
            for (int i = 0; i < params.length; i++) {
                //index从1开始
                ps.setObject(i+1,params[i]);
            }
        }
        return ps.executeQuery();
    }

    public static int executeNonQuery(String sql,Object...params) throws SQLException {
        int result = 0;
        conn=JDBCUtils.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        if (params != null) {
            for (int i = 0; i < params.length; i++) {
                ps.setObject(i+1, params[i]);
            }
        }
        result = ps.executeUpdate();
        JDBCUtils.close(conn,ps,null);
        return result;
    }
}
