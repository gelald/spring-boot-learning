package com.github.gelald.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author WuYingBin
 * date: 2023/4/21
 */
@Component
public class HandlerInterceptorB implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("请求到Controller前 执行HandlerInterceptorB逻辑");
        return true;
    }
}
