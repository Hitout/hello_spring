package com.gxyan.dbutils;

import org.apache.commons.dbcp2.BasicDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * 数据库连接和释放
 * @author gxyan
 */
public class JdbcUtils {
    private static DataSource ds;

    static {
        //初始化连接池
        try {
            InputStream inputStream = JdbcUtils.class.getClassLoader().getResourceAsStream("db.properties");
            Properties properties = new Properties();
            properties.load(inputStream);

            ds = BasicDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    public static Connection getConnection() throws SQLException {
        // 不会将真正的MySQL驱动返回的Connection返回给你
        return ds.getConnection();
    }

    public static void release(Connection conn, Statement stmt, ResultSet rs) {
        try {
            if (conn != null) {
                conn.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    /**
     * 抽取增删改的公共代码
     */
    public static void update(String sql, Object[] params) throws SQLException {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            conn = getConnection();

            st = conn.prepareStatement(sql);
            for (int i = 0; i < params.length; i++) {
                st.setObject(i+1, params[i]);
            }

            st.executeUpdate();
        } finally {
            release(conn, st, rs);
        }
    }

    /**
     * 抽取查询的公共代码，优化查询，替换掉所有的查询
     */
    public static Object query(String sql, Object[] params, ResultSetHandler handler) throws SQLException {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            st = conn.prepareStatement(sql);
            for (int i = 0; i < params.length; i++) {
                st.setObject(i+1, params[i]);
            }

            /*
             * 框架的设计者是不知道要执行的sql语句的，
             * 执行该sql语句，拿到的结果集，如何对结果集进行处理框架的设计者也是不知道的
             * 那该怎么办呢？框架的设计者不知道没关系，但使用该框架的人是知道怎么对结果集进行处理的，
             * 那就让他去做这种事情。
             * 代码该这样写：我对外暴露一个接口，使用该框架的人去实现该接口做这种事情，我针对接口进行调用。这是一种设计模式——策略模式
             */
            rs = st.executeQuery();

            return handler.handler(rs);

        } finally {
            release(conn, st, rs);
        }
    }
}
