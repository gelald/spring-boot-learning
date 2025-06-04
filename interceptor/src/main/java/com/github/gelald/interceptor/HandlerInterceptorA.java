package com.github.gelald.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * @author WuYingBin
 * date: 2023/4/5
 */
@Slf4j
@Component
public class HandlerInterceptorA implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("[Interceptor] ====== 请求到达 Controller 前，执行 preHandle 方法");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("[Interceptor] ====== Controller 逻辑执行完成后，执行 postHandle 方法");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info("[Interceptor] ====== 返回最终视图前，执行 afterCompletion 方法");
    }
}
