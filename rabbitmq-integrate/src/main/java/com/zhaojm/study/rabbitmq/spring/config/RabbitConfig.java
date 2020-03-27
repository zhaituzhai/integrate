package com.zhaojm.study.rabbitmq.spring.config;

import com.zhaojm.study.rabbitmq.spring.converter.ImageConverter;
import com.zhaojm.study.rabbitmq.spring.converter.WordConverter;
import com.zhaojm.study.rabbitmq.spring.delegate.MessageDelegate;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.amqp.support.converter.ContentTypeDelegatingMessageConverter;
import org.springframework.amqp.support.converter.DefaultJackson2JavaTypeMapper;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhaojm
 * @date 2020-03-27 10:51
 */
@Configurable
public class RabbitConfig {

    @Bean
    public ConnectionFactory connectionFactory () {
        CachingConnectionFactory cachingFactory = new CachingConnectionFactory();
        cachingFactory.setAddresses("127.0.0.1:5672");
        cachingFactory.setUsername("admin");
        cachingFactory.setPassword("1234");
        cachingFactory.setConnectionTimeout(10000);
        cachingFactory.setCloseTimeout(10000);
        return cachingFactory;
    }

    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory);
        //spring容器启动加载该类
        rabbitAdmin.setAutoStartup(true);
        return rabbitAdmin;
    }

    //=====================================申明三个交换机====================================================================
    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange("study.topic.exchange",true,false);
    }

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange("study.direct.exchange",true,false);
    }

    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange("study.fanout.exchange",true,false);
    }

    //===========================================申明队列===========================================================
    @Bean
    public Queue topicQueue() {
        return new Queue("study.topic.queue",true,false,false,null);
    }

    @Bean
    public Queue topicQueue2() {
        return new Queue("study.topic.queue",true,false,false,null);
    }

    @Bean
    public Queue directQueue() {
        return new Queue("study.direct.queue",true,false,false,null);
    }

    @Bean
    public Queue fanoutQueue() {
        return new Queue("study.fanout.queue",true,false,false,null);
    }

    @Bean
    public Queue orderQueue() {
        return new Queue("study.order.queue",true,false,false,null);
    }

    @Bean
    public Queue addressQueue() {
        return new Queue("study.address.queue",true,false,false,null);
    }

    @Bean
    public Queue fileQueue() {
        return new Queue("study.file.queue",true,false,false,null);
    }

    //========================================申明绑定==============================================================
    @Bean
    public Binding topicBinding() {
        return BindingBuilder.bind(topicQueue()).to(topicExchange()).with("topic.#");
    }

    @Bean
    public Binding topicBinding2() {
        return BindingBuilder.bind(topicQueue2()).to(topicExchange()).with("topic.key.#");
    }

    @Bean
    public Binding directBinding() {
        return BindingBuilder.bind(directQueue()).to(directExchange()).with("direct.key");
    }

    @Bean
    public Binding orderQueueBinding() {
        return BindingBuilder.bind(orderQueue()).to(directExchange()).with("rabbitmq.order");
    }

    @Bean
    public Binding addressQueueBinding() {
        return BindingBuilder.bind(addressQueue()).to(directExchange()).with("rabbitmq.address");
    }

    @Bean
    public Binding fileQueueBinding() {
        return BindingBuilder.bind(fileQueue()).to(directExchange()).with("rabbitmq.file");
    }

    @Bean
    public Binding fanoutBinding() {
        return BindingBuilder.bind(fanoutQueue()).to(fanoutExchange());
    }

    @Bean
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory());
        rabbitTemplate.setReceiveTimeout(50000);
        return rabbitTemplate;
    }

    /**
     * 简单的消息监听容器
     */
    @Bean
    public SimpleMessageListenerContainer simpleMessageListenerContainer() {
        SimpleMessageListenerContainer messageListenerContainer = new SimpleMessageListenerContainer();
        // 监听的队列
        messageListenerContainer.setQueues(topicQueue(), topicQueue2(), directQueue(), fanoutQueue(), orderQueue(), addressQueue(), fileQueue());
        // 消费者的数量
        messageListenerContainer.setConcurrentConsumers(5);
        //最大消费者数量
        messageListenerContainer.setMaxConcurrentConsumers(10);
        //签收模式
        messageListenerContainer.setAcknowledgeMode(AcknowledgeMode.AUTO);
        //设置拒绝重回队列
        messageListenerContainer.setDefaultRequeueRejected(false);

        /*
         1.设置默认的监听方法
         MessageListenerAdapter 默认方法 handleMessage
         MessageDelegate 中实现一个  handleMessage 的方法
         */
//        MessageListenerAdapter messageListenerAdapter = new MessageListenerAdapter(new MessageDelegate());
//        messageListenerContainer.setMessageListener(messageListenerAdapter);

        /*
         2.指定消费方法  adapter.setDefaultListenerMethod(methodName)
         */
//        MessageListenerAdapter messageListenerAdapter = new MessageListenerAdapter(new MessageDelegate());
//        messageListenerAdapter.setDefaultListenerMethod("consumerMessage");
//        messageListenerContainer.setMessageListener(messageListenerAdapter);


        /*
         3.不同的队列使用不同的方法，  adapter.setQueueOrTagToMethodName(Map<queue, methodName>)
         */
//        MessageListenerAdapter messageListenerAdapter = new MessageListenerAdapter(new MessageDelegate());
//        Map<String,String> queueMap = new HashMap<>();
//        queueMap.put("topicQueue","consumerTopicQueue");
//        queueMap.put("topicQueue2","consumerTopicQueue2");
//        messageListenerAdapter.setQueueOrTagToMethodName(queueMap);
//        messageListenerContainer.setMessageListener(messageListenerAdapter);

        /*
         4.处理 json
         */
//        MessageListenerAdapter messageListenerAdapter = new MessageListenerAdapter(new MessageDelegate());
//        // 1. 设置消费方法
//        messageListenerAdapter.setDefaultListenerMethod("consumerJsonMessage");
//        // 2.设置消息转换器
//        messageListenerAdapter.setMessageConverter(new Jackson2JsonMessageConverter());
//        messageListenerContainer.setMessageListener(messageListenerAdapter);

        /*
         5.处理java对象
         */
//        MessageListenerAdapter messageListenerAdapter = new MessageListenerAdapter(new MessageDelegate());
//        messageListenerAdapter.setDefaultListenerMethod("consumerJavaObjMessage");
//        // 对象转换器
//        Jackson2JsonMessageConverter jackson2JsonMessageConverter = new Jackson2JsonMessageConverter();
//        DefaultJackson2JavaTypeMapper javaTypeMapper = new DefaultJackson2JavaTypeMapper();
//        javaTypeMapper.setTrustedPackages("com.zhaojm.study.rabbitmq.spring.bean");
//        // 设置java转json的
//        jackson2JsonMessageConverter.setJavaTypeMapper(javaTypeMapper);
//        messageListenerAdapter.setMessageConverter(jackson2JsonMessageConverter);
//        messageListenerContainer.setMessageListener(messageListenerAdapter);


        /*
         6.自定义转换器   处理文件和图片
         */
        MessageListenerAdapter messageListenerAdapter = new MessageListenerAdapter(new MessageDelegate());
        messageListenerAdapter.setDefaultListenerMethod("consumerFileMessage");
        //设置转换器
        ContentTypeDelegatingMessageConverter messageConverter = new ContentTypeDelegatingMessageConverter();
        messageConverter.addDelegate("img/png",new ImageConverter());
        messageConverter.addDelegate("img/jpg",new ImageConverter());
        messageConverter.addDelegate("application/word",new WordConverter());
        messageConverter.addDelegate("word",new WordConverter());
        messageListenerAdapter.setMessageConverter(messageConverter);
        messageListenerContainer.setMessageListener(messageListenerAdapter);

        return messageListenerContainer;
    }
}
