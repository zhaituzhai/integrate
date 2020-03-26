package com.zhaojm.study.rabbitmq.convention.message;

import com.rabbitmq.client.*;
import com.zhaojm.study.rabbitmq.convention.utils.RabbitUtil;

import java.nio.charset.StandardCharsets;

/**
 * 声明队列在消费者，如果生产者没有声明队列，消息直接作废
 * @author zhaojm
 * @date 2020-03-25 13:31
 */
public class MessageConsumer {

    public static void main(String[] args) {

        ConnectionFactory factory = RabbitUtil.getFactory();
        // 获取连接以及mq信道
        try (Connection connection = factory.newConnection();
        Channel channel = connection.createChannel()) {
            // 声明队列
             /*
            queue:队列的名称
            durable:是否持久化, 队列的声明默认是存放到内存中的，如果rabbitmq重启会丢失，如果想重启之后还存在就要使队列持久化，
            保存到Erlang自带的Mnesia数据库中，当rabbitmq重启之后会读取该数据库
            exclusive:当连接关闭时connection.close()该队列是否会自动删除；
                二：该队列是否是私有的private，如果不是排外的，可以使用两个消费者都访问同一个队列，
                没有任何问题，如果是排外的，会对当前队列加锁，其他通道channel是不能访问的，如果强制访问会报异常
                com.rabbitmq.client.ShutdownSignalException: channel error; protocol method: #method<channel.cl
                一般等于true的话用于一个队列只能有一个消费者来消费的场景
            autodelete:是否自动删除，当最后一个消费者断开连接之后队列是否自动被删除，可以通过RabbitMQ Management，
                 查看某个队列的消费者数量，当consumers = 0时队列就会自动删除
             */
            channel.queueDeclare(MessageProducter.MESSAGE_QUEUE_NAME, false, false, false, null);
            // 声明消费者 获取消息
            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
                System.out.println("consumption ===> " + message);
            };
            // 监听队列
            channel.basicConsume(MessageProducter.MESSAGE_QUEUE_NAME, true, deliverCallback, consumerTag -> {});

        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
