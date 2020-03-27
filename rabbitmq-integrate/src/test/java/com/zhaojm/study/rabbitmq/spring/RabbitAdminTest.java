package com.zhaojm.study.rabbitmq.spring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author zhaojm
 * @date 2020-03-27 10:56
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitAdminTest {

    @Autowired
    private RabbitAdmin rabbitAdmin;

    @Test
    public void topicExchangeTest() {
        // 申明一个交换机
        String exchangeName = "rabbit-admin.topic.exchange";
        TopicExchange topicExchange = new TopicExchange(exchangeName, false, true);
        rabbitAdmin.declareExchange(topicExchange);

        // 申明一个队列
        String queueName = "rabbit-admin.topic.queue";
        Queue queue = new Queue(queueName, true);
        rabbitAdmin.declareQueue(queue);

        // 申明绑定
        String bindingName = "rabbit-admin.topic.binding";
        String routingKey = "rabbit-admin.#";
        // Binding binding = new Binding(bindingName, Binding.DestinationType.QUEUE, exchangeName, routingKey, null);
        // rabbitAdmin.declareBinding(binding);
        rabbitAdmin.declareBinding(BindingBuilder.bind(queue).to(topicExchange).with(routingKey));
    }

    @Test
    public void testDirectExchange() {
        DirectExchange directExchange = new DirectExchange("rabbit-admin.direct.exchange",true,false);
        rabbitAdmin.declareExchange(directExchange);
        Queue queue = new Queue("rabbit-admin.direct.queue",true);
        rabbitAdmin.declareQueue(queue);
        rabbitAdmin.declareBinding(BindingBuilder.bind(queue).to(directExchange).with("rabbit-admin.key.#"));
    }

}
