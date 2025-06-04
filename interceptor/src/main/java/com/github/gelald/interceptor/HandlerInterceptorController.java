package com.github.gelald.interceptor;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
    @Operation(summary = "经过所有 Interceptor")
    @GetMapping("/get")
    public String testFilter() {
        return "success";
    }

    @Operation(summary = "经过其中一个 Interceptor")
    @GetMapping("/exclude")
    public String testExclude() {
        return "exclude this path";
    }
}
