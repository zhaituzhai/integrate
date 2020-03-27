package com.zhaojm.study.rabbitmq.boot.producer.compent;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

/**
 * @author zhaojm
 * @date 2020/3/27 23:58
 */
@Slf4j
public class BootConfirmCallBack implements RabbitTemplate.ConfirmCallback {
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        log.info("correlationData:========>{},ack的标志{}", correlationData.getId(), ack);
        if (ack) {
            log.info("mq生产端消息已经成功投递到了broker,更新我们消息日志表");
        } else {
            log.warn("mq生产端没有被broker ack,原因:{}", cause);
        }
    }
}
