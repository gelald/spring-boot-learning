package com.github.gelald.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import jakarta.servlet.*;
import java.io.IOException;

/**
 * @author WuYingBin
 * date: 2023/4/4
 */
@Slf4j
@Component("filterC")
public class FilterC implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        //执行Filter逻辑
        log.info("[Filter] ====== 通过 DelegatingFilterProxyRegistrationBean 注册的 Filter");
        //让请求继续进入Filter链的下一个节点
        chain.doFilter(request, response);
    }
}
