package com.zhaojm.study.rabbitmq.convention.work;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;
import com.zhaojm.study.rabbitmq.convention.utils.RabbitUtil;

import java.nio.charset.StandardCharsets;

/**
 * @author zhaojm
 * @date 2020-03-25 16:25
 */
public class NewTask {
    private static final String TASK_QUEUE_NAME = "task_queue";

    public static void main(String[] args) {
        ConnectionFactory factory = RabbitUtil.getFactory();
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            /**
             * 每个消费者发送确认消息之前，消息队列不发送下一条消息到消费者，一次只处理一个消息
             *
             * 限制发送给同一个消费者 不得超过一条消息
             */
            int prefetchCount = 1;
            channel.basicQos(prefetchCount);
            channel.queueDeclare(TASK_QUEUE_NAME, true, false, false, null);
            String taskMessage = "";
            for (int i = 0; i < 50; i++) {
                taskMessage = String.format("attention this is NO.%s task", i);
                channel.basicPublish("", TASK_QUEUE_NAME, MessageProperties.PERSISTENT_TEXT_PLAIN,
                        taskMessage.getBytes(StandardCharsets.UTF_8));
                System.out.println(" [x] Sent '" + taskMessage + "'");
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
