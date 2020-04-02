package com.zhaojm.study.service;

import com.zhaojm.study.pojo.UserInfoDTO;

/**
 * @author zhaojm
 * @date 2020/4/2 23:00
 */
public interface IUserInfoService {
    UserInfoDTO getUserInfoById(Integer id);
    UserInfoDTO insertUserInfo(UserInfoDTO userInfo);
}
