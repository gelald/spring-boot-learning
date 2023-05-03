package com.github.gelald.filter;

import org.springframework.boot.web.servlet.DelegatingFilterProxyRegistrationBean;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Filter注册方式一：使用@ServletComponentScan注解扫描Filter包路径
 * @author WuYingBin
 * date: 2023/4/4
 */
@Configuration
@ServletComponentScan(basePackages = "com.github.gelald.filter")
public class FilterConfiguration {
    /**
     * Filter注册方式二：通过FilterRegistrationBean注册，方式一最终也是通过FilterRegistrationBean注册
     * 这种方式Filter不需要被Spring管理，直接把Filter实例传入FilterRegistrationBean中
     */
    @Bean
    public FilterRegistrationBean<FilterB> filterRegistrationBean() {
        FilterB filterB = new FilterB();
        FilterRegistrationBean<FilterB> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(filterB);
        //设置过滤器名、过滤规则
        filterRegistrationBean.setName("registration-bean-filter");
        filterRegistrationBean.addUrlPatterns("/demo/*");
        return filterRegistrationBean;
    }

    /**
     * Filter注册方式三：Filter被Spring管理，通过DelegatingFilterProxyRegistrationBean注册
     * 这种方式要求Filter被Spring管理，因为DelegatingFilterProxyRegistrationBean初始化时需要传入Filter的BeanName
     */
    @Bean
    public DelegatingFilterProxyRegistrationBean delegatingFilterProxyRegistrationBean() {
        DelegatingFilterProxyRegistrationBean delegatingFilterProxyRegistrationBean = new DelegatingFilterProxyRegistrationBean("filterC");
        delegatingFilterProxyRegistrationBean.setName("delegating-registration-bean-filter");
        delegatingFilterProxyRegistrationBean.addUrlPatterns("/demo/*");
        return delegatingFilterProxyRegistrationBean;
    }
}
