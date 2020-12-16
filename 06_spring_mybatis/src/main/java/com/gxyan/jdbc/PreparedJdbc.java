package com.gxyan.jdbc;

import java.sql.*;

/**
 *     1、PreparedStatement提供了参数化SQL的方式，使用PreparedStatement替代Statement对象可防止SQL注入
 *<br> 2、Connection提供事务控制
 *<br>      .setAutoCommit(false)：开启事务
 *<br>      .commit()：提交事务
 *<br>      .rollback()：回滚事务
 *<br>      .setSavepoint()：设置断点
 *<br> 3、可通过useCursorFetch=true开启客户端<strong>游标</strong>读取部分服务器端结果集，通过PreparedStatement的setFetchSize()方法设置读取数
 *
 * @author gxyan
 * @date 2020/12/13
 */
public class PreparedJdbc {
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/hello_spring?useSSL=false&serverTimezone=UTC";
    static final String USER = "gxyan";
    static final String PASSWORD = "";

    public static void preparedJdbc(String no) throws ClassNotFoundException {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        //1.装载驱动程序
        Class.forName(JDBC_DRIVER);
        //2.建立数据库连接
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            //3.执行SQL语句
            pst = conn.prepareStatement("select name from student where no = ?");
            pst.setString(1, no);
            // 设置每次从数据库中取10行
            pst.setFetchSize(10);
            rs = pst.executeQuery();
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
                if (pst != null) {
                    pst.close();
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
        preparedJdbc("001");
    }
}
