package com.zhaojm.study.controller;

import com.zhaojm.study.pojo.UserInfoDTO;
import com.zhaojm.study.service.IUserInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
@Api(value = "Hello", tags = {"helloController"})
public class HelloWorldController {

    @Autowired
    IUserInfoService userInfoService;

    @GetMapping(path = "/version")
    @ApiOperation("版本号")
    public String getVersion(){
        return "basic version 0.1";
    }

//    @GetMapping
    @GetMapping(path = "/user")
    @ApiOperation("根据Id取值用户信息")
    public UserInfoDTO getUserInfoById(@RequestParam(value = "id") @ApiParam Integer id){
        return userInfoService.getUserInfoById(id);
    }
}
