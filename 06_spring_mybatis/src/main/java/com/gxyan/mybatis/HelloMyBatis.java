package com.gxyan.mybatis;

import com.gxyan.entity.Student;
import com.gxyan.mybatis.utils.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;

/**
 * @author gxyan
 * @date 2020/12/13
 */
public class HelloMyBatis {
    public static void main(String[] args) {
        SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
        Student student = sqlSession.selectOne("com.gxyan.mybatis.mapper.StudentMapper.selectByNo", "001");
        System.out.println(student);
        sqlSession.close();
    }
}
