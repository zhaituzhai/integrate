package com.zhaojm.study.config.web;

import com.zhaojm.study.login.entity.SystemUserInfo;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static com.zhaojm.study.config.common.SystemConst.SESSION_USER;

/**
 * 拦截器
 * @author zhaojm
 * @date 2020/7/2 23:32
 */
@Component
public class SystemAuthenticationInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 用户信息获取
        SystemUserInfo loginUser = (SystemUserInfo)request.getSession().getAttribute(SESSION_USER);
        if(null == loginUser) {
            writeContent(response, "请登录");
            return false;
        }
        // 请求的url 判断权限
        if(null == loginUser.getAuthority() || loginUser.getAuthority().size() == 0) {
            writeContent(response, "没有权限");
            return false;
        }

        // 请求的url
        String requestURL = request.getRequestURL().toString().replace("http://localhost:8180","");
        if(loginUser.getAuthority().contains(requestURL)){
            return true;
        }

        writeContent(response, "没有访问权限");
        return false;
    }

    private void writeContent(HttpServletResponse response, String msg) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.print(msg);
        out.close();
    }
}
