package com.zhaojm.study.rabbitmq.convention.topic;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import com.zhaojm.study.rabbitmq.convention.utils.RabbitUtil;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author zhaojm
 * @date 2020-03-25 15:23
 */
public class TopicMessageConsumer {

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = RabbitUtil.getFactory();
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        //声明交换机
        String exchangeName = "study.topic.exchange";
        String exchangeType = "topic";
        channel.exchangeDeclare(exchangeName,exchangeType,true,true,null);

        //声明队列
        String queueName = "study.topic.queue";
        channel.queueDeclare(queueName,true,false,false,null);

        //声明绑定关系
        String bindingName = "study.binging.#";
        channel.queueBind(queueName,exchangeName,bindingName);

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody());
            System.out.println("接受到topic消息:" + message);
        };

        channel.basicConsume(queueName, false, deliverCallback, consumerTag -> {});

    }

}
