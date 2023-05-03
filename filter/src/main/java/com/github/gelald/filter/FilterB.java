package com.github.gelald.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author WuYingBin
 * date: 2023/4/4
 */
public class FilterB implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        //执行Filter逻辑
        System.out.println("进入通过FilterRegistrationBean注册的Filter");
        //让请求继续进入Filter链的下一个节点
        chain.doFilter(request, response);
    }
}
