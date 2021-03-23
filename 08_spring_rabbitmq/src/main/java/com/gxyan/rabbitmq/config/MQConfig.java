package com.gxyan.rabbitmq.config;

import com.gxyan.rabbitmq.constant.MQConstant;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 配置消息队列、交换机及其绑定关系
 * @author gxyan
 * @date 2021/3/22
 */
@Configuration
public class MQConfig {
    @Bean(MQConstant.Q_HELLO)
    public Queue helloQueue() {
        return new Queue(MQConstant.Q_HELLO);
    }

    @Bean(MQConstant.E_CONFIRM_MESSAGE)
    public Exchange testMessageExchange() {
        return ExchangeBuilder.topicExchange(MQConstant.E_CONFIRM_MESSAGE).durable(true).build();
    }

    @Bean(MQConstant.Q_CONFIRM_MESSAGE)
    public Queue testMessageQueue() {
        return QueueBuilder.durable(MQConstant.Q_CONFIRM_MESSAGE).build();
    }

    @Bean
    public Binding testMessageBind(@Qualifier(MQConstant.Q_CONFIRM_MESSAGE) Queue queue, @Qualifier(MQConstant.E_CONFIRM_MESSAGE) Exchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(MQConstant.R_CONFIRM_MESSAGE).noargs();
    }

    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(new Jackson2JsonMessageConverter());
        return factory;
    }
}
