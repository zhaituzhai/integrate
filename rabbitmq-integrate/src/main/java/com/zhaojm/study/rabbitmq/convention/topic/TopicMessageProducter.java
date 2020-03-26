package com.zhaojm.study.rabbitmq.convention.topic;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.zhaojm.study.rabbitmq.convention.utils.RabbitUtil;

/**
 * @author zhaojm
 * @date 2020-03-25 15:23
 */
public class TopicMessageProducter {

    public static void main(String[] args) {

        ConnectionFactory factory = RabbitUtil.getFactory();
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            String exchangeName = "study.topic.exchange";
            String routingKey1 = "study.topic.key1";
            String routingKey2 = "study.topic.key2";
            String routingKey3 = "study.topic.key.key3";

            channel.basicPublish(exchangeName, routingKey1, null, "I am a first message".getBytes());
            channel.basicPublish(exchangeName, routingKey2, null, "I am a second message".getBytes());
            channel.basicPublish(exchangeName, routingKey3, null, "I am a third message".getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
