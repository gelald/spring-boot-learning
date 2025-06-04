package com.github.gelald.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * @author WuYingBin
 * date: 2023/4/3
 */
@Slf4j
@WebFilter(filterName = "annotation-registration-filter", urlPatterns = "/demo/*")
public class FilterA implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //Web容器（如Tomcat）初始化Filter时调用，一般用于初始化一些资源
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        //执行Filter逻辑
        log.info("[Filter] ====== 通过 @WebFilter 注解注册的 Filter");
        //让请求继续进入Filter链的下一个节点
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        //Web容器（如Tomcat）销毁Filter时调用，一般用于资源关闭
        Filter.super.destroy();
    }
}
