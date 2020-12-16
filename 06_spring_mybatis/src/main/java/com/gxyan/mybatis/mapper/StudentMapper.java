package com.gxyan.mybatis.mapper;

import com.gxyan.entity.Major;
import com.gxyan.entity.Student;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author gxyan
 * @date 2020/12/13
 */
public interface StudentMapper {
    List<Student> selectStudents();

    Student selectByNo(@Param("no") String no);

    Major getByMajorId(@Param("majorId") Integer majorId);

}
