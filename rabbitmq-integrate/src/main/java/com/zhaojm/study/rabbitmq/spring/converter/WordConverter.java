package com.zhaojm.study.rabbitmq.spring.converter;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.support.converter.MessageConversionException;
import org.springframework.amqp.support.converter.MessageConverter;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.UUID;

/**
 * @author zhaojm
 * @date 2020/3/27 22:48
 */
public class WordConverter implements MessageConverter {
    @Override
    public Message toMessage(Object o, MessageProperties messageProperties) throws MessageConversionException {
        return null;
    }

    @Override
    public Object fromMessage(Message message) throws MessageConversionException {
        System.out.println("自定义的文档转换器................");
        String msgContentType = message.getMessageProperties().getContentType();
        String fileSuffix = "";
        if (msgContentType != null && msgContentType.contains("word")) {
            fileSuffix = "docx";
        } else {
            fileSuffix = "doc";
        }
        byte[] msgBody = message.getBody();
        String filePrefixName = UUID.randomUUID().toString();
        String filePath = "D:/test/" + filePrefixName + "." + fileSuffix;
        System.out.println("文件路径:" + filePath);

        File file = new File(filePath);
        try {
            Files.copy(new ByteArrayInputStream(msgBody), file.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }
}
