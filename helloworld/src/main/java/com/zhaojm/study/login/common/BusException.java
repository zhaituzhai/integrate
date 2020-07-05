package com.zhaojm.study.login.common;

/**
 * 业务统一异常
 *
 * @author zhaojm
 * @date 2020/7/5 19:54
 */
public class BusException extends RuntimeException {
    protected  ErrorEnum errorEnum;

    public BusException(ErrorEnum errorEnum) {
        super(errorEnum.getMessage());
        this.errorEnum = errorEnum;
    }

    public BusException(ErrorEnum errorEnum, String message) {
        super(message);
        this.errorEnum = errorEnum;
    }

    public ErrorEnum getErrorEnum() {
        return errorEnum;
    }
}
