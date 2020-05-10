package com.gxyan.cache.service;

import com.gxyan.cache.entity.Student;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

/**
 * @author gxyan
 * @date 2020/5/10 14:22
 */
public interface IStudentService {
    /**
     * 更新学生信息
     * @param student 学生信息
     * @return
     */
    Student update(Student student);

    /**
     * 删除学生信息
     * @param no 学号
     */
    void deleteByNo(String no);

    /**
     * 查询学生信息
     * @param no 学号
     * @return 学生信息
     */
    Student queryByNo(String no);
}
