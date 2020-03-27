package com.zhaojm.study.rabbitmq.spring.delegate;

import com.zhaojm.study.rabbitmq.bean.Order;

import java.io.File;
import java.util.Map;

/**
 * @author zhaojm
 * @date 2020-03-27 17:29
 */
public class MessageDelegate {

    public void handleMessage(String message) {
        System.out.println("MessageDelegate。。。。。。handleMessage => " + message);
    }

    public void consumerMessage(String message) {
        System.out.println("MessageDelegate。。。。。。consumerMessage => " + message);
    }

    public void consumerTopicQueue(String message) {
        System.out.println("MessageDelegate。。。。。。consumerTopicQueue => " + message);

    }

    public void consumerTopicQueue2(String message) {
        System.out.println("MessageDelegate。。。。。。consumerTopicQueue2 => " + message);

    }

    /**
     * 处理json
     * @param jsonMap
     */
    public void consumerJsonMessage(Map jsonMap) {
        System.out.println("MessageDelegate ===============处理json => " + jsonMap);
    }

    /**
     * 处理order
     * @param order
     */
    public void consumerJavaObjMessage(Order order) {
        System.out.println("MessageDelegate ================处理java对象 => " + order.toString());

    }

    public void consumerFileMessage(File file) {
        System.out.println("MessageDelegate================处理文件 => " + file.getName());
    }

}
