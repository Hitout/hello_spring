package com.gxyan.jdbc;

import java.sql.*;

/**
 * @author gxyan
 * @date 2020/12/13
 */
public class HelloJdbc {
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/hello_spring?useSSL=false&serverTimezone=UTC";
    static final String USER = "gxyan";
    static final String PASSWORD = "";

    public static void hello() throws ClassNotFoundException {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        //1.装载驱动程序
        Class.forName(JDBC_DRIVER);
        //2.建立数据库连接
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            //3.执行SQL语句
            stmt = conn.createStatement();
            rs = stmt.executeQuery("select name from student");
            //4.获取执行结果
            while (rs.next()) {
                System.out.println("Hello " + rs.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //5.清理环境
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
        hello();
    }
}
