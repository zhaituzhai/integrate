package com.zhaojm.study.aop;

import org.springframework.aop.ThrowsAdvice;

import java.io.IOException;
import java.io.UncheckedIOException;

/**
 * @author zhaojm
 * @date 2020/4/3 22:35
 */
public class CountingThrowsAdvice extends MethodCounter implements ThrowsAdvice {
    public void afterThrowing(IOException ex) throws Throwable {
        count(IOException.class.getName());
    }
    public void afterThrowing(UncheckedIOException ex) throws Throwable {
        count(UncheckedIOException.class.getName());
    }
}
