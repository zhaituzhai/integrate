package com.zhaojm.study.helloworld;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhaojm
 * @date 2020-03-24 10:55
 */
@RestController
@RequestMapping
public class HelloWorldController {

    @GetMapping(path = "version")
    public String getVersion(){
        return "basic version 0.1";
    }

}
