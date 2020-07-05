package com.zhaojm.study.login.service.impl;

import com.zhaojm.study.login.common.BusException;
import com.zhaojm.study.login.common.ErrorEnum;
import com.zhaojm.study.login.dto.LoginInfo;
import com.zhaojm.study.login.entity.SystemUserInfo;
import com.zhaojm.study.login.dao.SystemUserInfoDao;
import com.zhaojm.study.login.service.SystemUserInfoService;
import com.zhaojm.study.login.uitls.EncryptUtil;
import org.springframework.stereotype.Service;
import sun.security.provider.MD5;

import javax.annotation.Resource;
import java.util.*;

/**
 * 用户信息(SystemUserInfo)表服务实现类
 *
 * @author makejava
 * @since 2020-07-05 10:35:20
 */
@Service("systemUserInfoService")
public class SystemUserInfoServiceImpl implements SystemUserInfoService {
    @Resource
    private SystemUserInfoDao userInfoDao;

    /**
     * 通过ID查询单条数据
     *
     * @param userId 主键
     * @return 实例对象
     */
    @Override
    public SystemUserInfo queryById(Long userId) {
        return this.userInfoDao.queryById(userId);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<SystemUserInfo> queryAllByLimit(int offset, int limit) {
        return this.userInfoDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param systemUserInfo 实例对象
     * @return 实例对象
     */
    @Override
    public SystemUserInfo insert(SystemUserInfo systemUserInfo) {
        this.userInfoDao.insert(systemUserInfo);
        return systemUserInfo;
    }

    /**
     * 修改数据
     *
     * @param systemUserInfo 实例对象
     * @return 实例对象
     */
    @Override
    public SystemUserInfo update(SystemUserInfo systemUserInfo) {
        this.userInfoDao.update(systemUserInfo);
        return this.queryById(systemUserInfo.getUserId());
    }

    /**
     * 通过主键删除数据
     *
     * @param userId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long userId) {
        return this.userInfoDao.deleteById(userId) > 0;
    }

    @Override
    public SystemUserInfo checkLogin(LoginInfo loginInfo) {
        List<SystemUserInfo> userInfoList = this.userInfoDao.queryByUserName(loginInfo.getLoginAccount());
        if(userInfoList.size() <= 0) {
            throw new BusException(ErrorEnum.bus_data_error, "用户未注册");
        } else if (userInfoList.size() > 1) {
            throw new BusException(ErrorEnum.bus_param_error, "登录信息不唯一");
        }
        String password = EncryptUtil.toMd5(loginInfo.getLoginAccount().concat(loginInfo.getLoginPassword()));
        if(!password.equals(userInfoList.get(0).getUserPassword())) {
            throw new BusException(ErrorEnum.bus_data_error, "账号或密码错误");
        }
        return userInfoList.get(0);
    }

    @Override
    public void getAuthority(SystemUserInfo loginUser) {
        // TODO 权限列表查询
        loginUser.setAuthority(authorityMap.get(loginUser.getUserName()));
    }

    Map<String, Set<String>> authorityMap = new HashMap<String, Set<String>>(){{
        put("matte",new HashSet<String>(){{
            add("/systemUserInfo/selectOne");
        }});
    }};

}