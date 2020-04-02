package com.zhaojm.study.controller;

import com.zhaojm.study.pojo.UserInfoDTO;
import com.zhaojm.study.service.IUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhaojm
 * @date 2020-03-24 10:55
 */
@RestController
@RequestMapping
public class HelloWorldController {

    @Autowired
    IUserInfoService userInfoService;

    @GetMapping(path = "version")
    public String getVersion(){
        return "basic version 0.1";
    }

    @GetMapping(path = "/user")
    public UserInfoDTO getUserInfoById(@RequestParam(value = "id") Integer id){
        return userInfoService.getUserInfoById(id);
    }
}
