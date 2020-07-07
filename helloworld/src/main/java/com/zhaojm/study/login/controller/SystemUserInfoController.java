package com.zhaojm.study.login.controller;

import com.zhaojm.study.login.common.ResultDto;
import com.zhaojm.study.login.entity.SystemUserInfo;
import com.zhaojm.study.login.service.SystemUserInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户信息(SystemUserInfo)表控制层
 *
 * @author makejava
 * @since 2020-07-05 10:35:21
 */
@Api(value = "用户基础信息", tags = "用户操作")
@RestController
@RequestMapping
public class SystemUserInfoController {
    /**
     * 服务对象
     */
    @Resource
    private SystemUserInfoService systemUserInfoService;

    @GetMapping("selectOne")
    @ApiOperation(value = "通过主键查询单条数据")
    public ResultDto<SystemUserInfo> selectOne(@RequestParam @ApiParam("查询的用户id") Long id) {
        return ResultDto.valueSuccess(this.systemUserInfoService.queryById(id));
    }

    @GetMapping
    @ApiOperation(value = "查询所有用户信息")
    public ResultDto<List<SystemUserInfo>> selectAll() {
        return ResultDto.valueSuccess(this.systemUserInfoService.queryAllByLimit(0, 20));
    }


}