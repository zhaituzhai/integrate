package com.zhaojm.study.security.dao;

import com.zhaojm.study.security.entity.SystemUserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户信息(SystemUserInfo)表数据库访问层
 *
 * @author makejava
 * @since 2020-07-05 10:35:18
 */
@Mapper
public interface SystemUserInfoDao {

    /**
     * 通过ID查询单条数据
     *
     * @param userId 主键
     * @return 实例对象
     */
    SystemUserInfo queryById(Long userId);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<SystemUserInfo> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param systemUserInfo 实例对象
     * @return 对象列表
     */
    List<SystemUserInfo> queryAll(SystemUserInfo systemUserInfo);

    /**
     * 新增数据
     *
     * @param systemUserInfo 实例对象
     * @return 影响行数
     */
    int insert(SystemUserInfo systemUserInfo);

    /**
     * 修改数据
     *
     * @param systemUserInfo 实例对象
     * @return 影响行数
     */
    int update(SystemUserInfo systemUserInfo);

    /**
     * 通过主键删除数据
     *
     * @param userId 主键
     * @return 影响行数
     */
    int deleteById(Long userId);

    List<SystemUserInfo> queryByUserName(String userName);
}