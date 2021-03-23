package com.gxyan.rabbitmq.listener;

import com.gxyan.rabbitmq.constant.MQConstant;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * `@RabbitListener`: 标注在类或者方法上
 * `@RabbitHandler`: 标注在方法上，可通过重载方法接收同一队列的不同消息
 *
 * @author gxyan
 * @date 2021/3/21
 */
@Component
public class RabbitMQListener {
    @RabbitListener(queues = MQConstant.Q_HELLO)
    public void listenerQueue(String message) {
        System.out.println("接收消息：" + message);
    }

    /**
     * 消费确认模式
     * @param message
     * @param channel
     * @throws IOException
     */
    @RabbitListener(queues = MQConstant.Q_CONFIRM_MESSAGE)
    public void listenerConfirmQueue(Message message, Channel channel) throws IOException {
        long deliveryTag = message.getMessageProperties().getDeliveryTag();

        try {
            //1.接收转换消息
            String str = new String(message.getBody());
            if (str.length() > 5) {
                throw new RuntimeException("消息过长");
            }
            System.out.println(str);

            //2. 处理业务逻辑
            System.out.println("处理业务逻辑...");

            //3. 手动签收
            channel.basicAck(deliveryTag,true);
        } catch (Exception e) {
            System.out.println("出现异常，拒绝接受");
            //4.拒绝签收，不重回队列 requeue=false
            channel.basicNack(deliveryTag,true,false);
        }
    }
}
