package com.gxyan.rabbitmq.service.impl;

import com.gxyan.rabbitmq.constant.MQConstant;
import com.gxyan.rabbitmq.service.IRabbitService;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;

/**
 * @author gxyan
 * @date 2021/3/21
 */
@Service
public class RabbitServiceImpl implements IRabbitService {
    @Resource
    private RabbitTemplate rabbitTemplate;

    @Override
    public void sendMessage(String message) {
        // 设置部分请求参数
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setContentType(MessageProperties.CONTENT_TYPE_TEXT_PLAIN);

        // 发送消息（此处如果队列没有在RabbitMQ中创建，需创建或声明该队列的bean）
        rabbitTemplate.convertAndSend(MQConstant.Q_HELLO, new Message(message.getBytes(StandardCharsets.UTF_8), messageProperties));
    }
}
