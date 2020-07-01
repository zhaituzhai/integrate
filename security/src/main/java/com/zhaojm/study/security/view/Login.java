package com.zhaojm.study.security.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author zhaojm
 * @date 2020/7/1 23:39
 */
@Controller
public class Login {
    @GetMapping(value = "/login")
    public String login(){
        return "login";
    }
}
