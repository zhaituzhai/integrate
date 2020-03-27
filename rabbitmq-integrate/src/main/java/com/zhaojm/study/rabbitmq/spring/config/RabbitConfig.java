package com.zhaojm.study.rabbitmq.spring.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;

/**
 * @author zhaojm
 * @date 2020-03-27 10:51
 */
@Configurable
public class RabbitConfig {

    @Bean
    public ConnectionFactory connectionFactory () {
        CachingConnectionFactory cachingFactory = new CachingConnectionFactory();
        cachingFactory.setAddresses("127.0.0.1:5672");
        cachingFactory.setUsername("admin");
        cachingFactory.setPassword("1234");
        cachingFactory.setConnectionTimeout(10000);
        cachingFactory.setCloseTimeout(10000);
        return cachingFactory;
    }

    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory);
        //spring容器启动加载该类
        rabbitAdmin.setAutoStartup(true);
        return rabbitAdmin;
    }

}
