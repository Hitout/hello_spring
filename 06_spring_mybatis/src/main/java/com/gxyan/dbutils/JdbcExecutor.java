package com.gxyan.dbutils;

import com.gxyan.entity.Student;

import java.sql.SQLException;
import java.util.List;

/**
 * 封装、简化JDBC操作
 * @author gxyan
 */
public class JdbcExecutor {
    public void add(Student a) throws SQLException {
        String sql = "insert into student(no, name) values(?,?)";
        Object[] params = {a.getNo(), a.getName()};
        JdbcUtils.update(sql, params);
    }

    public void delete(String no) throws SQLException {
        String sql = "delete from student where no=?";
        Object[] params = {no};
        JdbcUtils.update(sql, params);
    }

    public void update(Student a) throws SQLException {
        String sql = "update student set name=? where no=?";
        Object[] params = {a.getName(), a.getNo()};
        JdbcUtils.update(sql, params);
    }

    public Student find(String no) throws SQLException {
        String sql = "select no, name from student where no=?";
        Object[] params = {no};
        return (Student) JdbcUtils.query(sql, params, new BeanHandler(Student.class));
    }

    public List getAll() throws SQLException {
        String sql = "select no, name from student";
        Object[] params = { };
        return (List) JdbcUtils.query(sql, params, new BeanListHandler(Student.class));
    }
}
