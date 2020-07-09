package com.zhaojm.study.config.common;

/**
 * 错误枚举
 *
 * @author zhaojm
 * @date 2020/7/5 10:47
 */
public enum ErrorEnum {
    success(0, "成功"),
    sys_error_other(-1, "系统未知异常"),
    sys_default_error(1, "默认异常"),

    /* 业务异常 */
    bus_param_error(11001, "业务参数异常"),
    bus_rule_error(11002, "业务规则异常"),
    bus_operate_error(11003, "业务操作异常"),
    bus_data_error(11003, "业务数据异常"),
    ;

    private int code;
    private String message;

    ErrorEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
