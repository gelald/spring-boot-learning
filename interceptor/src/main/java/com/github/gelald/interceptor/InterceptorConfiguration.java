package com.github.gelald.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author WuYingBin
 * date: 2023/4/5
 */
@Configuration
public class InterceptorConfiguration implements WebMvcConfigurer {
    @Autowired
    @Qualifier("handlerInterceptorA")
    private HandlerInterceptor handlerInterceptorA;
    @Autowired
    @Qualifier("handlerInterceptorB")
    private HandlerInterceptor handlerInterceptorB;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //注册拦截器
        registry.addInterceptor(handlerInterceptorA)
                //设置拦截的路径
                .addPathPatterns("/demo/**")
                //设置不拦截的路径（排除这些路径）
                .excludePathPatterns("/demo/exclude")
                //设置拦截器的优先级
                .order(2);
        registry.addInterceptor(handlerInterceptorB)
                .addPathPatterns("/demo/**")
                .order(1);
    }
}
