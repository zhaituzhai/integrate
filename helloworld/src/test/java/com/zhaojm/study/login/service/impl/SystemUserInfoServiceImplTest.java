package com.zhaojm.study.login.service.impl;

import com.zhaojm.study.login.uitls.EncryptUtil;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author zhaojm
 * @date 2020/7/5 21:43
 */
class SystemUserInfoServiceImplTest {

    @Test
    public void testPassword() {
        System.out.println(EncryptUtil.toMd5("matte1234"));
    }

}