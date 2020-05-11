package com.zhaojm.study.bplus.service.impl;

import com.zhaojm.study.bplus.dao.IUserMapper;
import com.zhaojm.study.bplus.pojo.UserInfo;
import com.zhaojm.study.bplus.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zhaojm
 * @date 2020-04-17 10:26
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    IUserMapper userMapper;

    @Override
    public UserInfo getUserById(int userId) {
        return userMapper.selectById(userId);
    }
}
