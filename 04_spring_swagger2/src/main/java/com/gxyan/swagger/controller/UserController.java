package com.gxyan.swagger.controller;

import com.gxyan.swagger.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ·@Api：修饰整个类，描述Controller的作用
 * ·@ApiOperation：描述一个类的一个方法，或者说一个接口
 * ·@ApiParam：单个参数描述
 * ·@ApiModel：用对象来接收参数
 * ·@ApiProperty：用对象接收参数时，描述对象的一个字段
 * ·@ApiResponse：HTTP响应其中1个描述
 * ·@ApiResponses：HTTP响应整体描述
 * ·@ApiIgnore：使用该注解忽略这个API
 * ·@ApiError：发生错误返回的信息
 * ·@ApiImplicitParam：一个请求参数
 * ·@ApiImplicitParams：多个请求参数
 * @author gxyan
 * @date 2020/5/10 23:03
 */
@Api(value = "用户Controller")
@RestController
@RequestMapping("user")
public class UserController {

    @ApiIgnore
    @GetMapping("hello")
    public String hello() {
        return "hello";
    }

    @ApiOperation(value = "获取用户信息", notes = "根据用户id获取用户信息")
    @ApiImplicitParam(name = "id", value = "用户id", required = true, dataType = "Long", paramType = "path")
    @GetMapping("/{id}")
    public User getUserById(@PathVariable(value = "id") Long id) {
        User user = new User();
        user.setId(id);
        user.setName("gxyan");
        user.setAge(25);
        return user;
    }

    @ApiOperation(value = "获取用户列表", notes = "获取用户列表")
    @GetMapping("/list")
    public List<User> getUserList() {
        List<User> list = new ArrayList<>();
        User user1 = new User();
        user1.setId(1L);
        user1.setName("gxyan");
        user1.setAge(25);
        list.add(user1);
        User user2 = new User();
        user2.setId(2L);
        user2.setName("scott");
        user2.setAge(29);
        list.add(user2);
        return list;
    }

    @ApiOperation(value = "新增用户", notes = "根据用户实体创建用户")
    @ApiImplicitParam(name = "user", value = "用户实体", required = true, dataType = "User")
    @PostMapping("/add")
    public Map<String, Object> addUser(@RequestBody User user) {
        Map<String, Object> map = new HashMap<>();
        map.put("result", "success");
        return map;
    }

    @ApiOperation(value = "删除用户", notes = "根据用户id删除用户")
    @ApiImplicitParam(name = "id", value = "用户id", required = true, dataType = "Long", paramType = "path")
    @DeleteMapping("/{id}")
    public Map<String, Object> deleteUser(@PathVariable(value = "id") Long id) {
        Map<String, Object> map = new HashMap<>();
        map.put("result", "success");
        return map;
    }

    @ApiOperation(value = "更新用户", notes = "根据用户id更新用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户id", required = true, dataType = "Long", paramType = "path"),
            @ApiImplicitParam(name = "user", value = "用户实体", required = true, dataType = "User") })
    @PutMapping("/{id}")
    public Map<String, Object> updateUser(@PathVariable(value = "id") Long id, @RequestBody User user) {
        Map<String, Object> map = new HashMap<>();
        map.put("result", "success");
        return map;
    }
}
