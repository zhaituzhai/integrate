package com.zhaojm.study.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jasypt.encryption.StringEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author zhaojm
 * @date 2020-05-11 10:31
 */
@RestController
@RequestMapping
@Api(value = "加密处理", tags = {"工具类"})
public class EncryptController {

    @Autowired
    StringEncryptor stringEncryptor;

    @GetMapping
    @ApiOperation(value = "获取对应密文")
    public String getEncryptResult(@RequestParam String sourceString) {
        return stringEncryptor.encrypt(sourceString);
    }

}
