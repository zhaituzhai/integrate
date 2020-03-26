package com.zhaojm.study.rabbitmq.convention.utils;

import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author zhaojm
 * @date 2020-03-25 11:52
 */
public class RabbitUtil {

    public static ConnectionFactory getFactory() {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("127.0.0.1");
        factory.setPort(5672);
        factory.setUsername("admin");
        factory.setPassword("1234");
        return factory;
    }

}
