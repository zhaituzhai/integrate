package com.zhaojm.study.aop;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * @author zhaojm
 * @date 2020/4/3 22:23
 */
public class CountingBeforeAdvice extends MethodCounter implements MethodBeforeAdvice {
    @Override
    public void before(Method method, Object[] objects, Object o) throws Throwable {
        count(method);
    }
}
