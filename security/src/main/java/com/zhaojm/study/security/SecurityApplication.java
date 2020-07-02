package com.zhaojm.study.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class SecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecurityApplication.class, args);
    }

    @GetMapping(value = "/hello")
    public String hello() {
        return "hello security!";
    }

    @PostMapping
    public String login(@RequestParam(value = "username") String username,
                        @RequestParam(value = "password") String password) {
        if(username.length() > 0){
            if ("1234".equals(password)) {
                return "hello user";
            }
        }
        return "";
    }

}
