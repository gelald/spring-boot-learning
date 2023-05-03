package com.github.gelald.interceptor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author WuYingBin
 * date: 2023/4/21
 */
@RestController
@RequestMapping("/demo")
public class HandlerInterceptorController {
    @GetMapping("/get")
    public String testFilter() {
        return "success";
    }

    @GetMapping("/exclude")
    public String testExclude() {
        return "exclude this path";
    }
}
