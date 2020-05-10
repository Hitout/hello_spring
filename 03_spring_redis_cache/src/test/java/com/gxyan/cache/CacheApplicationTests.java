package com.gxyan.cache;

import com.gxyan.cache.entity.Student;
import com.gxyan.cache.service.IStudentService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class CacheApplicationTests {
    @Resource
    private IStudentService studentService;

    @Test
    public void test() {
        Student student1 = studentService.queryByNo("001");
        System.out.println("学号" + student1.getNo() + "的学生姓名为：" + student1.getName());

        Student student2 = studentService.queryByNo("001");
        System.out.println("学号" + student2.getNo() + "的学生姓名为：" + student2.getName());
    }

}
