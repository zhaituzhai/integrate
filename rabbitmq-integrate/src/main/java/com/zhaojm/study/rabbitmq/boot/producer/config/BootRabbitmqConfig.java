package com.zhaojm.study.rabbitmq.boot.producer.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhaojm
 * @date 2020/3/27 23:52
 */
@Configuration
public class BootRabbitmqConfig {
    @Bean
    public DirectExchange tulingBootDirectExchange() {
        return new DirectExchange("springboot.direct.exchange", true, false);
    }

    @Bean
    public CustomExchange delayExchange() {
        Map<String, Object> args = new HashMap<>();
        args.put("x-delayed-type", "direct");
        return new CustomExchange("delayExchange", "x-delayed-message", true, false, args);
    }

    @Bean
    public Queue bootQueue() {
        return new Queue("bootQueue", true, false, false);
    }

    @Bean
    public Queue clusterQueue() {
        return new Queue("clusterQueue", true, false, false);
    }

    @Bean
    public Queue bootDelayQueue() {
        return new Queue("bootDelayQueue", true, false, false);
    }

    @Bean
    public Binding bootBinder() {
        return BindingBuilder.bind(bootQueue()).to(tulingBootDirectExchange()).with("springboot.key");
    }

    @Bean
    public Binding clusterBinder() {
        return BindingBuilder.bind(clusterQueue()).to(tulingBootDirectExchange()).with("rabbitmq.cluster.key");
    }

    @Bean
    public Binding binding() {
        return BindingBuilder.bind(bootDelayQueue()).to(delayExchange()).with("springboot.delay.key").noargs();
    }
}
