package com.github.gelald.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @author WuYingBin
 * date: 2023/4/3
 */
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
        System.out.println("进入通过注解注册的Filter");
        //让请求继续进入Filter链的下一个节点
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        //Web容器（如Tomcat）销毁Filter时调用，一般用于资源关闭
        Filter.super.destroy();
    }
}
