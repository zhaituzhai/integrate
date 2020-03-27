package com.zhaojm.study.rabbitmq.boot;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhaojm.study.rabbitmq.bean.Order;
import com.zhaojm.study.rabbitmq.boot.producer.compent.BootMessageSender;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author zhaojm
 * @date 2020/3/28 0:06
 */
public class BootRabbitMqTest {
    @Test
    public void contextLoads() {
    }

    @Autowired
    private BootMessageSender messageSender;

    @Test
    public void testMsgSender() throws JsonProcessingException {
        /*
           创建消息属性
         */
        Map<String,Object> msgProp = new HashMap<>();
        msgProp.put("company","freedom");
        msgProp.put("name","matt");

        Order order = Order.builder()
                .orderNo(UUID.randomUUID().toString())
                .orderDate(new Date())
                .payMoney(new BigDecimal("10000.00"))
                .username("matt")
                .build();
        ObjectMapper objectMapper = new ObjectMapper();

        String orderJson = objectMapper.writeValueAsString(order);

        messageSender.sendMsg(orderJson,msgProp);
    }

    @Test
    public void testSenderOrder() throws Exception {
        Order order = Order.builder()
                .orderNo(UUID.randomUUID().toString())
                .orderDate(new Date())
                .payMoney(new BigDecimal("10000.00"))
                .username("matt")
                .build();

        //直接发送对象
        messageSender.sendOrderMsg(order);
    }

    @Test
    public void testSenderDelay() {
        Order order = Order.builder()
                .orderNo(UUID.randomUUID().toString())
                .orderDate(new Date())
                .payMoney(new BigDecimal("10000.00"))
                .username("matt")
                .build();

        messageSender.sendDelayMessage(order);
    }

    @Test
    public void sendMsgToCluster() throws JsonProcessingException {
        /*
          创建消息属性
         */
        Map<String,Object> msgProp = new HashMap<>();
        msgProp.put("company","freedom");
        msgProp.put("name","matt");

        Order order = Order.builder()
                .orderNo(UUID.randomUUID().toString())
                .orderDate(new Date())
                .payMoney(new BigDecimal("10000.00"))
                .username("matt")
                .build();
        ObjectMapper objectMapper = new ObjectMapper();

        String orderJson = objectMapper.writeValueAsString(order);

        messageSender.sendMsgToCluster(orderJson,msgProp);
    }
}
