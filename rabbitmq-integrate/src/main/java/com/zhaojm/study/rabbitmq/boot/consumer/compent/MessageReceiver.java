package com.zhaojm.study.rabbitmq.boot.consumer.compent;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import com.zhaojm.study.rabbitmq.bean.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author zhaojm
 * @date 2020/3/27 23:37
 */
@Component
@Slf4j
public class MessageReceiver {

    @RabbitListener(queues = {"bootQueue"})
    public void consumerMessage(Message message, Channel channel) throws IOException {
        System.out.println(Thread.currentThread().getName() + " 接收到来自bootQueue：");
        log.info("监听bootQueue消费消息=======:{}", new String(message.getBody()));
        //手工签收
        Long deliveryTag = (Long) message.getMessageProperties().getDeliveryTag();
        channel.basicAck(deliveryTag, false);
    }


    /**
     * 消费延时消息
     * @param message
     * @param channel
     * @throws IOException
     */
    @RabbitListener(queues = {"bootDelayQueue"})
    public void consumerDelayMessage(Message message, Channel channel) throws IOException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        ObjectMapper objectMapper = new ObjectMapper();
        Order order = objectMapper.readValue(message.getBody(), Order.class);
        log.info("在{},签收:{}", sdf.format(new Date()), order);

        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }


    /**
     *  强烈不推荐这种用法 我们再开发中需要把队列 交换机  绑定配置到我们专门的配置类中
     * @param message
     * @param channel
     */
    @RabbitListener(bindings =
    @QueueBinding(
            value = @Queue(
                    name = "bootQueue2",
                    durable = "true",
                    autoDelete = "false",
                    exclusive = "false"
            ),
            exchange = @Exchange(
                    name = "springboot.direct.exchange",
                    type = "direct",
                    durable = "true",
                    autoDelete = "false"),
            key = {"springboot.key2"}
    )
    )
    public void consumerMessage2(Message message, Channel channel) throws IOException {
        System.out.println(Thread.currentThread().getName() + " 接收到来自bootQueue：");
        log.info("监听bootQueue2消费消息=======:{}", new String(message.getBody()));
        //手工签收
        Long deliveryTag = (Long) message.getMessageProperties().getDeliveryTag();
        channel.basicAck(deliveryTag, false);
    }


    /**
     * 接受客户端发送的core包下的message
     *
     * @param message
     * @param channel
     * @throws IOException
     */
    @RabbitListener(queues = {"bootQueue3"})
    public void consumerOrder(Message message, Channel channel) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Order order = objectMapper.readValue(message.getBody(), Order.class);
        log.info("监听bootQueue3消费消息:{}", order.toString());
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }

    @RabbitListener(queues = {"clusterQueue"})
    public void clusterQueue(Message message, Channel channel) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Order order = objectMapper.readValue(message.getBody(), Order.class);
        log.info("监听clusterQueue消费消息:{}", order.toString());
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }
}
