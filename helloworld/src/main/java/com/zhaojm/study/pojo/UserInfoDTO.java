package com.zhaojm.study.pojo;

import java.io.Serializable;

/**
 * @author zhaojm
 */
public class UserInfoDTO implements Serializable {
    private Integer userId;

    private String username;

    private Integer age;

    private static final long serialVersionUID = 1L;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}