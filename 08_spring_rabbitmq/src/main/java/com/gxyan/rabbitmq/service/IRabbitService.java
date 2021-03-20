package com.gxyan.rabbitmq.service;

/**
 * @author gxyan
 * @date 2021/3/21
 */
public interface IRabbitService {
    void sendMessage(String message);
}
