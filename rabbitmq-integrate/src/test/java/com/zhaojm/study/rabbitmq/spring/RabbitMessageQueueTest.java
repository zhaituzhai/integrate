package com.zhaojm.study.rabbitmq.spring;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhaojm.study.rabbitmq.bean.Order;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
import java.util.UUID;

/**
 * @author zhaojm
 * @date 2020/3/27 22:31
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitMessageQueueTest {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void testRabbitmqTemplate() {

        MessageProperties messageProperties = new MessageProperties();
        messageProperties.getHeaders().put("company", "freedom");
        messageProperties.getHeaders().put("name", "matt");

        String msgBody = "hello world";
        Message message = new Message(msgBody.getBytes(), messageProperties);


        //不需要message对象发送
        rabbitTemplate.convertAndSend("study.direct.queue", "direct.key", message);
    }

    @Test
    public void simpleMessageListenerContainerTest() {
        rabbitTemplate.convertAndSend("study.topic.exchange", "topic.hello", "你好 测试");
    }


    @Test
    public void messageListenerAdaperQueueOrTagToMethodName() {
        rabbitTemplate.convertAndSend("study.topic.exchange", "topic.hello", "你好 测试");
        rabbitTemplate.convertAndSend("study.topic.exchange", "topic.key.hello", "你好 测试 开发");
    }

    @Test
    public void sendJson() throws JsonProcessingException {

        Order order = Order.builder()
                .orderNo(UUID.randomUUID().toString())
                .orderDate(new Date())
                .payMoney(new BigDecimal("10000.00"))
                .username("matt")
                .build();

        ObjectMapper objectMapper = new ObjectMapper();
        String orderJson = objectMapper.writeValueAsString(order);

        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setContentType("application/json");
        Message orderMsg = new Message(orderJson.getBytes(), messageProperties);
        rabbitTemplate.convertAndSend("study.direct.exchange", "rabbitmq.order", orderMsg);

    }

    @Test
    public void sendJavaObj() throws JsonProcessingException {

        Order order = Order.builder()
                .orderNo(UUID.randomUUID().toString())
                .orderDate(new Date())
                .payMoney(new BigDecimal("10000.00"))
                .username("matt")
                .build();

        ObjectMapper objectMapper = new ObjectMapper();
        String orderJson = objectMapper.writeValueAsString(order);

        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setContentType("application/json");
        messageProperties.getHeaders().put("__TypeId__", "Order");
        Message orderMsg = new Message(orderJson.getBytes(), messageProperties);
        rabbitTemplate.convertAndSend("study.direct.exchange", "rabbitmq.order", orderMsg);

    }


    @Test
    public void sendImage() throws IOException {
        byte[] imgBody = Files.readAllBytes(Paths.get("D:/test/file01", "test.png"));
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setContentType("img/png");
        Message message = new Message(imgBody, messageProperties);
        rabbitTemplate.send("study.direct.exchange", "rabbitmq.file", message);

    }

    @Test
    public void sendWord() throws IOException {
        byte[] imgBody = Files.readAllBytes(Paths.get("D:/test/file01", "spring.docx"));
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setContentType("application/word");
        Message message = new Message(imgBody, messageProperties);
        rabbitTemplate.send("study.direct.exchange", "rabbitmq.file", message);

    }

}
