package com.zhaojm.study.rabbitmq.convention.message;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.zhaojm.study.rabbitmq.convention.utils.RabbitUtil;

/**
 * @author zhaojm
 * @date 2020-03-25 11:50
 */
public class MessageProducter {

    public static final String MESSAGE_QUEUE_NAME = "convention-queue";
    public static void main(String[] args) {
        ConnectionFactory factory = RabbitUtil.getFactory();

        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.queueDeclare(MESSAGE_QUEUE_NAME, false, false, false, null);
            for (int i = 0; i < 5; i++) {
                String message = "Hello world" + i;
                /*
                但是在这里我们没有指定交换机?那我们的消息发送到哪里了？？？？
                The default exchange is implicitly bound to every queue, with a routing key equal to the queue name.
                It is not possible to explicitly bind to, or unbind from the default exchange. It also cannot be deleted.
                说明:加入我们消息发送的时候没有指定具体的交换机的话，那么就会发送到rabbimtq指定默认的交换机上，
                那么该交换机就会去根据routing_key 查找对应的queueName 然后发送的该队列上.
                 */
                channel.basicPublish("", MESSAGE_QUEUE_NAME, null, message.getBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
