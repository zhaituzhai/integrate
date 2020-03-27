package com.zhaojm.study.rabbitmq.bean;

import lombok.*;

/**
 * @author zhaojm
 * @date 2020-03-27 17:04
 */
@Getter
@Setter
@Builder
@ToString(of = {"provinces", "city", "area"})
public class Address {
    private String provinces;
    private String city;
    private String area;
}
