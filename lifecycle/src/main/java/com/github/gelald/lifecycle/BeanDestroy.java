package com.github.gelald.lifecycle;

import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;


/**
 * @author WuYingBin
 * date: 2022/12/26
 */
@Slf4j
public class BeanDestroy implements DisposableBean {

    @PreDestroy
    public void preDestroy() {
        // 1
        log.info("[destroy] ===== 第一步 ===== [jakarta模块 -- @PreDestroy]");
    }

    @Override
    public void destroy() {
        // 2
        log.info("[destroy] ===== 第二步 ===== [DisposableBean接口 -- destroy方法]");
    }

    public void destroyMethod() {
        // 3
        log.info("[destroy] ===== 第三步 ===== [@Bean注解指定 -- destroyMethod方法]");
    }
}
