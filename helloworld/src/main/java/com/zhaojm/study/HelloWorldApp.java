package com.zhaojm.study;

import com.zhaojm.study.config.swagger.SwaggerConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author zhaojm
 * @date  2020-03-24 10:40
 */
// 这个注释告诉Spring Boot根据你添加的jar依赖关系“猜测”你想要如何配置Spring。由于spring-boot-starter-web添加了
// Tomcat和Spring MVC，因此自动配置假定您正在开发Web应用程序并相应地设置Spring。
// @EnableAutoConfiguration
// @RestController
// 启动 Spring boot 的自动装配
@SpringBootApplication
@Import(SwaggerConfig.class)
public class HelloWorldApp {

    /*@RequestMapping("/")
    public String home(){
        return "hello world";
    }*/

    public static void main(String[] args) {
        // 完全禁用重新启动支持
        // System.setProperty("spring.devtools.restart.enabled", "false");

        /*
          我们的主要方法是通过调用run来委托Spring Boot的SpringApplication类。
          SpringApplication引导我们的应用程序，从Spring开始，然后启动自动配置的Tomcat Web服务器。
          我们需要将 HelloWorldApp.class 作为参数传递给run方法，以告诉SpringApplication哪个是主要的Spring组件。
          还会传递args数组以公开任何命令行参数。
         */
        SpringApplication.run(HelloWorldApp.class, args);
    }
}
