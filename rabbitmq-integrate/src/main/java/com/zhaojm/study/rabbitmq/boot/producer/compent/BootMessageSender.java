package com.zhaojm.study.rabbitmq.boot.producer.compent;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhaojm.study.rabbitmq.bean.Order;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;
import java.util.UUID;

/**
 * @author zhaojm
 * @date 2020/3/27 23:57
 */
public class BootMessageSender {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 测试发送我们的消息
     * @param msg 消息内容
     * @param msgProp 消息属性
     */
    public void sendMsg(String msg, Map<String,Object> msgProp) {
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.getHeaders().putAll(msgProp);
        //构建消息对象
        Message message = new Message(msg.getBytes(),messageProperties);
        //构建correlationData 用于做可靠性投递得,ID:必须为全局唯一的 根据业务规则
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        //开启确认模式
        rabbitTemplate.setConfirmCallback(new BootConfirmCallBack());
        //开启消息可达监听
        rabbitTemplate.setReturnCallback(new BootReturnCallBack());

        //错误的交换机
        //rabbitTemplate.convertAndSend("springboot.direct.exchange.asdfasdfasdf","springboot.key",message,correlationData);
        //错误的队列
        rabbitTemplate.convertAndSend("springboot.direct.exchange","springboot.key2asdfasdfasfasdfsfasdf",message,correlationData);
        //rabbitTemplate.convertAndSend("springboot.direct.exchange","springboot.key",message,correlationData);
    }

    public void sendOrderMsg(Order order) throws Exception {
        //构建correlationData 用于做可靠性投递得,ID:必须为全局唯一的 根据业务规则
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        //开启确认模式
        rabbitTemplate.setConfirmCallback(new BootConfirmCallBack());
        //开启消息可达监听
        rabbitTemplate.setReturnCallback(new BootReturnCallBack());
        /*
         使用org.springframework.amqp.core.Message 包装对象发送
         */
        ObjectMapper objectMapper = new ObjectMapper();
        String orderJson = objectMapper.writeValueAsString(order);
        MessageProperties messageProperties = new MessageProperties();
        Message message = new Message(orderJson.getBytes(),messageProperties);
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
        rabbitTemplate.convertAndSend("springboot.direct.exchange","springboot.key3",message,correlationData);


        //直接发送对象
        rabbitTemplate.convertAndSend("springboot.direct.exchange","springboot.key2",order,correlationData);
    }


    public void sendDelayMessage(Order order) {
        //构建correlationData 用于做可靠性投递得,ID:必须为全局唯一的 根据业务规则
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        //开启确认模式
        rabbitTemplate.setConfirmCallback(new BootConfirmCallBack());

        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());

        rabbitTemplate.convertAndSend("delayExchange", "springboot.delay.key", order, new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                message.getMessageProperties().setHeader("x-delay", 10000);//设置延迟时间
                return message;
            }
        }, correlationData);

    }


    public void sendMsgToCluster(String msg,Map<String,Object> msgProp) {
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.getHeaders().putAll(msgProp);
        //构建消息对象
        Message message = new Message(msg.getBytes(),messageProperties);
        //构建correlationData 用于做可靠性投递得,ID:必须为全局唯一的 根据业务规则
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        //开启确认模式
        rabbitTemplate.setConfirmCallback(new BootConfirmCallBack());
        //开启消息可达监听
        rabbitTemplate.setReturnCallback(new BootReturnCallBack());
        rabbitTemplate.convertAndSend("springboot.direct.exchange","rabbitmq.cluster.key",message,correlationData);
    }
}
