package com.zhaojm.study.rabbitmq.spring.config;

import com.zhaojm.study.rabbitmq.spring.config.RabbitConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author zhaojm
 * @date 2020-03-27 11:12
 */
public class SpringRabbitApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(RabbitConfig.class);
    }
}
