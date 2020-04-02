package com.zhaojm.study.service.impl;

import com.zhaojm.study.mapper.IUserInfoMapper;
import com.zhaojm.study.pojo.UserInfoDTO;
import com.zhaojm.study.service.IUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author zhaojm
 * @date 2020/4/2 23:00
 */
@Service
public class UserInfoServiceImpl implements IUserInfoService {

    @Autowired
    IUserInfoMapper userInfoMapper;

    @Override
    public UserInfoDTO getUserInfoById(Integer id) {
        return userInfoMapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional
    public UserInfoDTO insertUserInfo(UserInfoDTO userInfo) {
        userInfoMapper.insertSelective(userInfo);
        return userInfo;
    }
}
