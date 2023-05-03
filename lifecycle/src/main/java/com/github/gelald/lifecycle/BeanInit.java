package com.github.gelald.lifecycle;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;

/**
 * @author WuYingBin
 * date: 2022/12/26
 */
@Slf4j
public class BeanInit implements InitializingBean {
    public BeanInit() {
        // 1
        log.info("BeanInit【构造方法】执行");
    }

    @PostConstruct
    public void postConstruct() {
        // 2
        log.info("【@PostConstruct修饰的方法】执行");
    }

    @Override
    public void afterPropertiesSet() {
        // 3
        log.info("InitializingBean接口的【afterPropertiesSet方法】执行");
    }

    public void initMethod() {
        // 4
        log.info("@Bean注解中指定的【initMethod方法】执行");
    }
}
