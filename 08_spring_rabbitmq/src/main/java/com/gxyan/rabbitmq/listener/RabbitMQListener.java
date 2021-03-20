package com.gxyan.rabbitmq.listener;

import com.gxyan.rabbitmq.constant.MQConstant;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * `@RabbitListener`: 标注在类或者方法上
 * `@RabbitHandler`: 标注在方法上，可通过重载方法接收同一队列的不同消息
 *
 * @author gxyan
 * @date 2021/3/21
 */
@Component
public class RabbitMQListener {
    @RabbitListener(queues = MQConstant.Q_HELLO)@RabbitHandler
    public void listenerQueue(String message) {
        System.out.println("接收消息：" + message);
    }
}
