package com.zhaojm.study.login.controller;

import com.zhaojm.study.login.common.ResultDto;
import com.zhaojm.study.login.common.SystemConst;
import com.zhaojm.study.login.dto.LoginInfo;
import com.zhaojm.study.login.entity.SystemUserInfo;
import com.zhaojm.study.login.service.SystemUserInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static com.zhaojm.study.login.common.SystemConst.SESSION_USER;

/**
 * @author zhaojm
 * @date 2020/7/5 10:38
 */
@RestController
@Api(value = "用户登录操作", tags = "用户操作")
public class LoginController {

    @Resource
    private SystemUserInfoService  userInfoService;

    @PostMapping
    @ApiOperation(value = "用户登录")
    public ResultDto<SystemUserInfo> login(LoginInfo loginInfo, HttpServletRequest request) {
        if(null == loginInfo || StringUtils.isEmpty(loginInfo.getLoginAccount()) || StringUtils.isEmpty(loginInfo.getLoginPassword())) {
            return ResultDto.valueError("登录信息不能为空");
        }
        // 登录信息校验
        SystemUserInfo loginUser = userInfoService.checkLogin(loginInfo);

        // 权限信息获取
        userInfoService.getAuthority(loginUser);

        // 登录信息放入session
        HttpSession session = request.getSession();
        session.setAttribute(SESSION_USER, loginUser);
        return ResultDto.valueSuccess(loginUser);
    }

    @GetMapping
    @ApiOperation(value = "用户登出")
    public ResultDto<Boolean> loginOut(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.invalidate();
        return ResultDto.valueSuccess();
    }

    @GetMapping
    @ApiOperation(value = "获取登录人信息")
    public ResultDto<SystemUserInfo> getLoginInfo(HttpServletRequest request) {
        HttpSession session = request.getSession();
        SystemUserInfo loginUserInfo = (SystemUserInfo) session.getAttribute(SESSION_USER);
        return ResultDto.valueSuccess(loginUserInfo);
    }

}
