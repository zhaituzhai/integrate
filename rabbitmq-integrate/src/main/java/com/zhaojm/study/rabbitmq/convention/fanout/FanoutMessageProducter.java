package com.zhaojm.study.rabbitmq.convention.fanout;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.zhaojm.study.rabbitmq.convention.utils.RabbitUtil;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author zhaojm
 * @date 2020-03-26 18:25
 */
public class FanoutMessageProducter {

    private static final String FANOUT_QUEUE = "fanout-queue-name";

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = RabbitUtil.getFactory();
        //创建连接 信道
        try(Connection connection = factory.newConnection();
        Channel channel = connection.createChannel()) {
            //定义交换机名称
            String exchangeName = "study.fanout-exchange";
            //定义routingKey
            String routingKey = "";
            String messageBody = "hello world fanout message ";
            for (int i = 0; i < 1000; i++) {

                channel.basicPublish(exchangeName, "123", null, (messageBody + i).getBytes());
            }
        }
    }
}
