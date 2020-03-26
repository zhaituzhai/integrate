package com.zhaojm.study.rabbitmq.convention.direct;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.zhaojm.study.rabbitmq.convention.utils.RabbitUtil;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author zhaojm
 * @date 2020-03-25 15:22
 */
public class MessageProducter {

    public static void main(String[] args) {
        //创建连接
        ConnectionFactory factory = RabbitUtil.getFactory();
        try(Connection connection = factory.newConnection();
            Channel channel = connection.createChannel()) {

            String exchangeName = "study.direct-change";
            String routingKey = "study.direct-change.key";
            //消息体内容
            String messageBody = "hello world direct change";
            channel.basicPublish(exchangeName, routingKey, null, messageBody.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
