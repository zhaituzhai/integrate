package com.zhaojm.study.security.controller;

import com.zhaojm.study.config.common.ResultDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhaojm
 * @date 2020/7/5 10:38
 */
@RestController
@RequestMapping
@Api(value = "用户登录操作", tags = "用户操作")
public class UserInfoController {

    @PostMapping
    @ApiOperation(value = "欢迎接口")
    public ResultDto<String> index() {
        return ResultDto.valueSuccess("欢迎来到安全系统");
    }

}
