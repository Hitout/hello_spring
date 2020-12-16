package com.gxyan.jdbc;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.*;

/**
 * @author gxyan
 * @date 2020/12/13
 */
public class DBPoolTest {
    public static BasicDataSource ds = null;

    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/hello_spring?useSSL=false";
    static final String USER = "gxyan";
    static final String PASSWORD = "";

    /**
     * 创建DBCP连接池
     */
    public static void dbPoolInit() {
        ds = new BasicDataSource();
        ds.setUrl(DB_URL);
        ds.setDriverClassName(JDBC_DRIVER);
        ds.setUsername(USER);
        ds.setPassword(PASSWORD);
    }

    /**
     * 使用DBCP连接数据库
     */
    public void dbPoolTest() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            conn = ds.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery("select name from student");
            while (rs.next()) {
                System.out.println("Hello " + rs.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
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

    }

    public static void main(String[] args) throws ClassNotFoundException {
        dbPoolInit();
        new DBPoolTest().dbPoolTest();
    }
}
