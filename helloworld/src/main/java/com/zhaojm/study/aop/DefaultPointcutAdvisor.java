package com.zhaojm.study.aop;

import org.aopalliance.aop.Advice;
import org.springframework.aop.Pointcut;
import org.springframework.aop.support.AbstractGenericPointcutAdvisor;

import java.io.Serializable;

/**
 * @author zhaojm
 * @date 2020/4/3 23:10
 */
public class DefaultPointcutAdvisor extends AbstractGenericPointcutAdvisor implements Serializable {
    private Pointcut pointcut = Pointcut.TRUE;

    public DefaultPointcutAdvisor() {
    }

    public DefaultPointcutAdvisor(Advice advice) {
        this(Pointcut.TRUE, advice);
    }

    public DefaultPointcutAdvisor(Pointcut pointcut, Advice advice) {
        this.pointcut = pointcut;
        setAdvice(advice);
    }

    public void setPointcut(Pointcut pointcut) {
        this.pointcut = null != pointcut ? pointcut : Pointcut.TRUE;
    }

    @Override
    public Pointcut getPointcut() {
        return this.pointcut;
    }

    @Override
    public String toString() {
        return getClass().getName() + ": pointcut [" + getPointcut() + "]; advice [" + getAdvice() +"]";
    }
}
