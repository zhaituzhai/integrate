package com.zhaojm.study.security.dto;

import java.io.Serializable;

/**
 * @author zhaojm
 * @date 2020/7/5 18:47
 */
public class LoginInfo implements Serializable {
    private String loginAccount;
    private String loginPassword;
    private static final long serialVersionUID = 2878874540477790533L;

    public String getLoginAccount() {
        return loginAccount;
    }

    public void setLoginAccount(String loginAccount) {
        this.loginAccount = loginAccount;
    }

    public String getLoginPassword() {
        return loginPassword;
    }

    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword;
    }
}
