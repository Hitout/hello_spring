package com.gxyan.exception.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Spring Boot默认的出错处理机制会先匹配/error目录下的页面
 * {@link org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController#errorHtml}
 * @author gxyan
 * @date 2020/5/13 22:34
 */
@RestController
@RequestMapping("user")
public class UserController {

    @GetMapping("/{id:\\d+}")
    public void getUser(@PathVariable String id) {
        throw new RuntimeException("User Not Exists");
    }
}
