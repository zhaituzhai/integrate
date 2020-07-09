package com.zhaojm.study.security.configure;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/**
 * @author zhaojm
 * @date 2020/7/1 22:56
 */
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 定义用户信息服务
     */
    @Bean
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername("zhaojm").password("123").authorities("p1").build());
        manager.createUser(User.withUsername("matte").password("111").authorities("p2").build());
        return manager;
    }

    /**
     * 密码编辑器
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    /**
     * 安全拦截机制
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/userInfo/**").authenticated() // 所有 /userInfo/** 的请求必须认证通过
                .anyRequest().permitAll()  // 除了 /userInfo/** ，其他请求都可以访问
                .and()
                .formLogin() // 允许表单登录
                .successForwardUrl("/userInfo/index"); // 自定义登录成功的页面地址  对应路径需要是post请求
                //.and()
                // Spring Security提供的跨站请求伪造防护功能
                //.csrf().disable();
    }
    /*

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

     */
}
