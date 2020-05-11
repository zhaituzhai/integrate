package com.zhaojm.study.bplus.service;

import com.zhaojm.study.bplus.pojo.UserInfo;

/**
 * @author zhaojm
 * @date 2020-04-17 10:23
 */
public interface IUserService {

    UserInfo getUserById(int userId);
}
