package com.zhaojm.study.aop;

import org.springframework.aop.AfterReturningAdvice;

import java.lang.reflect.Method;

/**
 * @author zhaojm
 * @date 2020/4/3 22:34
 */
public class CountingAfterReturningAdvice extends MethodCounter implements AfterReturningAdvice {
    @Override
    public void afterReturning(Object o, Method method, Object[] objects, Object o1) throws Throwable {
        count(method);
    }
}
