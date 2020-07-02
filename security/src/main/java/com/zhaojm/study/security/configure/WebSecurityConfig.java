package com.zhaojm.study.security.configure;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author zhaojm
 * @date 2020/7/1 22:56
 */
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/css/**", "/js/**","/img/**", "/webjars/**", "**/favicon.ico", "/index").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/index") // 使登录页面不设限访问
                .loginProcessingUrl("/login") // 指定处理登录请求的路径
                .successHandler((httpServletRequest, httpServletResponse, authentication) -> {
                    httpServletResponse.setContentType("application/json;charset=UTF-8");
                    PrintWriter out = httpServletResponse.getWriter();
                    out.write("{\"error_code\":\"0\",\"message\":\"欢迎登录系统\"}");
                })
                .failureHandler((httpServletRequest, httpServletResponse, e) -> {
                    httpServletResponse.setStatus(401);
                    PrintWriter out = httpServletResponse.getWriter();
                    out.write("{\"error_code\":\"401\",\"name\":\""+e.getClass()+"\",\"message\":\""+ e.getMessage()+"\"}");
                })
                .permitAll()
                .and()
                // Spring Security提供的跨站请求伪造防护功能
                .csrf().disable();
    }
}
