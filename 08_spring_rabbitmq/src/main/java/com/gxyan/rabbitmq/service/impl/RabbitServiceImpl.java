package com.gxyan.rabbitmq.service.impl;

import com.gxyan.rabbitmq.constant.MQConstant;
import com.gxyan.rabbitmq.service.IRabbitService;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.connection.CorrelationData;
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

    @Override
    public void sendConfirmMessage(String str) {
        // 设置消息确认回调函数。correlationData：配置信息；ack：是否成功接收消息；cause：失败原因
        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
            if (ack) {
                System.out.println("接收消息成功");
            } else {
                System.out.println("接收消息失败：" + cause);
            }
        });

        // 设置退回函数。message：消息对象；replyCode：错误码；replyText：错误信息；exchange：交换机；routingKey：路由键；
        rabbitTemplate.setReturnCallback((message, replyCode, replyText, exchange, routingKey) -> {
            System.out.println("消息对象"+ message +"；错误码"+ replyCode +"；错误信息"+ replyText +"；交换机"+ exchange +"；路由键"+ routingKey);
        });

        rabbitTemplate.convertAndSend(MQConstant.E_CONFIRM_MESSAGE, MQConstant.R_CONFIRM_MESSAGE, str);
    }
}
