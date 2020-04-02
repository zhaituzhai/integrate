package com.zhaojm.study.mapper;

import com.zhaojm.study.pojo.UserInfoDTO;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author zhaojm
 */
@Mapper
public interface IUserInfoMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(UserInfoDTO record);

    int insertSelective(UserInfoDTO record);

    UserInfoDTO selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(UserInfoDTO record);

    int updateByPrimaryKey(UserInfoDTO record);
}