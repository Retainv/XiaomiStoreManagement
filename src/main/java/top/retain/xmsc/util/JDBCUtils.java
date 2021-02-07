package top.retain.xmsc.util;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * @author Retain
 * @date 2021/1/10 18:58
 */

/**
 * JDBCUtil工具类，没有数据库操作
 */
public class JDBCUtils {
    private static String driverClassName=null;
    private static String url=null;
    private static String userName=null;
    private static String password=null;

    public JDBCUtils() {
    }
    static {
        InputStream inputStream = JDBCUtils.class.getClassLoader().getResourceAsStream("db.properties");
        Properties properties = new Properties();
        try {
            properties.load(inputStream);
            driverClassName=properties.getProperty("driverClass");
            url=properties.getProperty("url");
            userName = properties.getProperty("userName");
            password=properties.getProperty("password");

            Class.forName(driverClassName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url,userName,password);
    }

    public static void close(Connection conn, PreparedStatement ps, ResultSet rs){
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}
