package com.github.gelald.filter;

import jakarta.servlet.*;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * @author WuYingBin
 * date: 2023/4/4
 */
@Slf4j
public class FilterB implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        //执行Filter逻辑
        log.info("[Filter] ====== 通过 FilterRegistrationBean 注册的 Filter");
        //让请求继续进入Filter链的下一个节点
        chain.doFilter(request, response);
    }
}
