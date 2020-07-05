package com.zhaojm.study.login.common;

import java.io.Serializable;

/**
 * @author zhaojm
 * @date 2020/7/5 10:39
 */
public class ResultDto<T> implements Serializable {
    protected String message;
    protected T value;
    protected boolean isSuccess;
    protected int code;
    private static final long serialVersionUID = -4214236501903574966L;
    public ResultDto() {
    }

    public ResultDto(String message, T value, boolean isSuccess, int code) {
        this.message = message;
        this.value = value;
        this.isSuccess = isSuccess;
        this.code = code;
    }

    public static <T> ResultDto<T> valueSuccess() {
        return valueSuccess(ErrorEnum.success.getMessage(), null, true, ErrorEnum.success.getCode());
    }

    public static <T> ResultDto<T> valueSuccess(T value) {
        return valueSuccess(ErrorEnum.success.getMessage(), value, true, ErrorEnum.success.getCode());
    }


    public static <T> ResultDto<T> valueSuccess(String message, T value, boolean isSuccess, int code) {
        return new ResultDto<>(message, value, isSuccess, code);
    }

    public static <T> ResultDto<T> valueError(String message) {
        return valueError(message, null, false, ErrorEnum.sys_default_error.getCode());
    }

    public static <T> ResultDto<T> valueError(ErrorEnum errorEnum) {
        return valueError(errorEnum.getMessage(), null, false, errorEnum.getCode());
    }

    public static <T> ResultDto<T> valueError(ErrorEnum errorEnum, String message) {
        return valueError(message, null, false, errorEnum.getCode());
    }

    public static <T> ResultDto<T> valueError(String message, T value) {
        return valueError(message, value, false, ErrorEnum.sys_default_error.getCode());
    }

    public static <T> ResultDto<T> valueError(String message, T value, boolean isSuccess, int code) {
        return new ResultDto<>(message, value, isSuccess, code);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
