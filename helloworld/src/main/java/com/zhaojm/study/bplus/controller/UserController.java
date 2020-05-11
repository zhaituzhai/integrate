package com.zhaojm.study.bplus.controller;

import com.zhaojm.study.bplus.pojo.UserInfo;
import com.zhaojm.study.bplus.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhaojm
 * @date 2020-04-17 10:29
 */
@RestController
@RequestMapping
@Api(value = "User", tags = {"UserController"})
public class UserController {

    @Autowired
    IUserService userService;

    @GetMapping
    @ApiOperation("根据id查找信息")
    public UserInfo getUserById(@RequestParam Integer userId){
        return userService.getUserById(userId);
    }

}
