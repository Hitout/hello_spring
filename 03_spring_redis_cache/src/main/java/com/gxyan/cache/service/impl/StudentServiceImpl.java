package com.gxyan.cache.service.impl;

import com.gxyan.cache.entity.Student;
import com.gxyan.cache.mapper.StudentMapper;
import com.gxyan.cache.service.IStudentService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 1、@CacheConfig(cacheNames = "student")：配置该对象中返回的内容存储于名为student的缓存中
 * 2、@Cacheable(key = "")：先从缓存中获取，若不存在则从数据库获取，并将返回值加入缓存中
 * 3、@CachePut(key = "")：每次都会真实调用函数，并缓存方法的返回值
 * 4、@CacheEvict(key = "")：从缓存中移除相应数据
 * @author gxyan
 * @date 2020/5/10 14:23
 */
@Service
@CacheConfig(cacheNames = "student")
public class StudentServiceImpl implements IStudentService {
    @Resource
    private StudentMapper studentMapper;

    @Override
    @CachePut(key = "#p0.no")
    public Student update(Student student) {
        studentMapper.update(student);
        return studentMapper.queryByNo(student.getNo());
    }

    @Override
    @CacheEvict(key = "#p0", allEntries = true)
    public void deleteByNo(String no) {
        studentMapper.deleteByNo(no);
    }

    @Override
    @Cacheable(key = "#p0")
    public Student queryByNo(String no) {
        return studentMapper.queryByNo(no);
    }
}
