package com.zhaojm.study.security.intercept;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 拦截器
 * @author zhaojm
 * @date 2020/7/2 23:32
 */
@Component
public class SimpleAuthenticationInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object obj = request.getSession().getAttribute("_user");
        if(null == obj) {
            writeContent(response, "请登录");
        }
        // 请求的url 判断权限
        if(null != obj) {
            return true;
        }

        writeContent(response, "没有权限");

        return false;
    }

    private void writeContent(HttpServletResponse response, String msg) throws IOException {
        PrintWriter out = response.getWriter();
        out.print(msg);
        out.close();
    }
}
