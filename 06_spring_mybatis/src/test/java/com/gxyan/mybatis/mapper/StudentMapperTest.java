package com.gxyan.mybatis.mapper;

import com.gxyan.entity.Major;
import com.gxyan.entity.Student;
import com.gxyan.mybatis.utils.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StudentMapperTest {

    @Test
    void selectStudents() {
    }

    @Test
    void selectByNo() {
        SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
        //获取OrderMapper代理实现
        StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);

        List<Student> list = studentMapper.selectStudents();
        for (Student student : list) {
            System.out.println(student);
        }
        sqlSession.close();
    }

    @Test
    void getByMajorId() {
        SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
        //获取OrderMapper代理实现
        StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);

        Major major = studentMapper.getByMajorId(1);
        System.out.println(major);

        sqlSession.close();
    }
}