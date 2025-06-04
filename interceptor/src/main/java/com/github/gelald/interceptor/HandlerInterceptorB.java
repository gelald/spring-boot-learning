package com.github.gelald.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * @author WuYingBin
 * date: 2023/4/21
 */
@Slf4j
@Component
public class HandlerInterceptorB implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("[Interceptor] ====== 请求到达 Controller 前，执行 preHandle 方法");
        return true;
    }
}
