package com.gxyan.cache.mapper;

import com.gxyan.cache.entity.Student;
import org.apache.ibatis.annotations.*;
import org.springframework.cache.annotation.CacheConfig;

/**
 * @author gxyan
 */
@Mapper
public interface StudentMapper {
    @Update("update student set name=#{name}, sex=#{sex} where no=#{no}")
    int update(Student student);

    @Delete("delete from student where no=#{no}")
    int deleteByNo(String no);

    @Select("select * from student where no=#{no}")
    @Results(id = "student", value = { @Result(property = "no", column = "no", javaType = String.class),
            @Result(property = "name", column = "name", javaType = String.class),
            @Result(property = "sex", column = "sex", javaType = String.class) })
    Student queryByNo(String no);
}