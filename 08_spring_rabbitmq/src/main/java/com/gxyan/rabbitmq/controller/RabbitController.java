package com.gxyan.rabbitmq.controller;

import com.gxyan.rabbitmq.service.IRabbitService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author gxyan
 * @date 2021/3/21
 */
@RestController
@RequestMapping("rabbit")
public class RabbitController {
    @Resource
    private IRabbitService rabbitService;

    @GetMapping("send/{message}")
    public void sendMessage(@PathVariable(value = "message") String message) {
        rabbitService.sendMessage(message);
    }
}
