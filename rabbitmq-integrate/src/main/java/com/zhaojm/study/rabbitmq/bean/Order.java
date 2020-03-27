package com.zhaojm.study.rabbitmq.bean;

import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author zhaojm
 * @date 2020-03-27 16:55
 */
@Setter
@Getter
@Builder
@NoArgsConstructor
@ToString(of = {"orderNo", "orderDate", "payMoney", "username"})
public class Order {
    private String orderNo;
    private Date orderDate;
    private BigDecimal payMoney;
    private String username;
}
