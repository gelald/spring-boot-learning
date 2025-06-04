package com.github.gelald.lifecycle;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;


/**
 * @author WuYingBin
 * date: 2022/12/26
 */
@Slf4j
public class BeanInit implements InitializingBean {
    public BeanInit() {
        // 1
        log.info("[init] ===== 第一步 ===== [java -- 构造方法]");
    }

    @PostConstruct
    public void postConstruct() {
        // 2
        log.info("[init] ===== 第二步 ===== [jakarta模块 -- @PostConstruct]");
    }

    @Override
    public void afterPropertiesSet() {
        // 3
        log.info("[init] ===== 第三步 ===== [InitializingBean接口 -- afterPropertiesSet方法]");
    }

    public void initMethod() {
        // 4
        log.info("[init] ===== 第四步 ===== [@Bean注解指定 -- initMethod方法]");
    }
}
