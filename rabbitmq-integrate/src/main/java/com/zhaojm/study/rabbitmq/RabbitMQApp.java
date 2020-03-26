package com.zhaojm.study.rabbitmq;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author zhaojm
 * @date 2020-03-25 11:46
 */
@SpringBootApplication
@EnableRabbit
public class RabbitMQApp {
    public static void main(String[] args) {
        SpringApplication.run(RabbitMQApp.class, args);
    }
}
