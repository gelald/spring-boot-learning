package com.github.gelald.lifecycle;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;

import javax.annotation.PreDestroy;

/**
 * @author WuYingBin
 * date: 2022/12/26
 */
@Slf4j
public class BeanDestroy implements DisposableBean {

    @PreDestroy
    public void preDestroy() {
        // 1
        log.info("【@PreDestroy修饰的方法】执行");
    }

    @Override
    public void destroy() {
        // 2
        log.info("DisposableBean接口的【destroy方法】执行");
    }

    public void destroyMethod() {
        // 3
        log.info("@Bean注解中指定的【destroyMethod方法】执行");
    }
}
