package com.gxyan.dbutils;

import com.gxyan.entity.Student;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

class JdbcExecutorTest {
    JdbcExecutor executor = new JdbcExecutor();

    @Test
    void update() throws SQLException {
        Student student = new Student("001", "Jack", "M", 1);
        executor.update(student);
    }

    @Test
    void find() throws SQLException {
        Student student = executor.find("001");
        System.out.println(student);
    }

    @Test
    void getAll() throws SQLException {
        List<Student> students = executor.getAll();
        System.out.println(students);
    }
}