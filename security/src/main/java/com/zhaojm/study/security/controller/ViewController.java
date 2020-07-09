package com.zhaojm.study.security.controller;

import com.zhaojm.study.config.common.ResultDto;
import com.zhaojm.study.security.entity.SystemUserInfo;
import com.zhaojm.study.security.service.SystemUserInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author zhaojm
 * @date 2020/7/1 23:39
 */
@RestController
@RequestMapping
@Api(value = "页面模板", tags = {"页面模板"})
public class ViewController {

    @Resource
    private SystemUserInfoService userInfoService;

    @GetMapping
    @ApiOperation(value = "login")
    public ResultDto<SystemUserInfo> login(){
        SystemUserInfo userInfo = userInfoService.queryById(1L);
        return ResultDto.valueSuccess(userInfo);
    }
}
