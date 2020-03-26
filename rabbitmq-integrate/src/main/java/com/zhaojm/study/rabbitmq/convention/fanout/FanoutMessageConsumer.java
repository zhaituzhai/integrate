package com.zhaojm.study.rabbitmq.convention.fanout;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import com.zhaojm.study.rabbitmq.convention.utils.RabbitUtil;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

/**
 * @author zhaojm
 * @date 2020-03-26 18:32
 */
public class FanoutMessageConsumer {

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = RabbitUtil.getFactory();
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        //声明队列
        String exchangeName = "study.fanout-exchange";
        String exchangeType = "fanout";
        channel.exchangeDeclare(exchangeName,exchangeType,true,true,null);

        String queueName = "study.fanout.queue";
        channel.queueDeclare(queueName,true,false,false,null);

        //声明绑定关系
        String bindStr = "fanout-bind";
        channel.queueBind(queueName, exchangeName, bindStr);

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody());
            System.out.println("接受到消息:" + message);
        };

        channel.basicConsume(queueName, false, deliverCallback, consumerTag -> {});
    }

}
