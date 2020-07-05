package com.zhaojm.study.login.service;

import com.zhaojm.study.login.dto.LoginInfo;
import com.zhaojm.study.login.entity.SystemUserInfo;
import java.util.List;
import java.util.Set;

/**
 * 用户信息(SystemUserInfo)表服务接口
 *
 * @author makejava
 * @since 2020-07-05 10:35:20
 */
public interface SystemUserInfoService {

    /**
     * 通过ID查询单条数据
     *
     * @param userId 主键
     * @return 实例对象
     */
    SystemUserInfo queryById(Long userId);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<SystemUserInfo> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param systemUserInfo 实例对象
     * @return 实例对象
     */
    SystemUserInfo insert(SystemUserInfo systemUserInfo);

    /**
     * 修改数据
     *
     * @param systemUserInfo 实例对象
     * @return 实例对象
     */
    SystemUserInfo update(SystemUserInfo systemUserInfo);

    /**
     * 通过主键删除数据
     *
     * @param userId 主键
     * @return 是否成功
     */
    boolean deleteById(Long userId);

    /**
     * 登录
     *
     * @param loginInfo 登录信息
     * @return 登录用户信息
     */
    SystemUserInfo checkLogin(LoginInfo loginInfo);

    /**
     * 获取全新列表
     *
     * @param loginUser
     * @return
     */
    void getAuthority(SystemUserInfo loginUser);

}