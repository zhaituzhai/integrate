package com.zhaojm.study.bplus.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhaojm.study.bplus.pojo.UserInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author zhaojm
 * @date 2020-04-17 10:08
 */
@Mapper
public interface IUserMapper extends BaseMapper<UserInfo> {
}
