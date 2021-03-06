package com.zhaojm.study.controller;

import com.zhaojm.study.pojo.GoodsInfoDTO;
import com.zhaojm.study.pojo.UserInfoDTO;
import com.zhaojm.study.service.IUserInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

/**
 * @author zhaojm
 * @date 2020-03-24 10:55
 */
@RestController
@RequestMapping
@Api(value = "Hello", tags = {"helloController"})
public class HelloWorldController {

    private static String staticTest = "";

    @Value("${test.database}")
    private String test;

    @Autowired
    IUserInfoService userInfoService;

    @GetMapping(path = "/version")
    @ApiOperation("版本号")
    public String getVersion(){
        return "basic version 0.1 - zhaojm ==> " + staticTest + " |||| " + test;
    }

    @GetMapping
    @ApiOperation("根据Id取值用户信息")
    public UserInfoDTO getUserInfoById(@RequestParam(value = "id") @ApiParam Integer id){
        return userInfoService.getUserInfoById(id);
    }
    @Value("${test.database}")
    public void setStaticTest(String test){
        HelloWorldController.staticTest = test;
    }

    @GetMapping
    @ApiOperation("获取BigDecimal")
    public GoodsInfoDTO getGoodsInfo(){
        GoodsInfoDTO goods = new GoodsInfoDTO();
        goods.setAmount(new BigDecimal("2342"));
        goods.setQuantity(new BigDecimal("29932342321231"));
        goods.setPrice(goods.getAmount().divide(goods.getQuantity(), 15, BigDecimal.ROUND_HALF_UP));
        return goods;
    }
}
