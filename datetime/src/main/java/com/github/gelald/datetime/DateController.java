package com.github.gelald.datetime;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.extensions.Extension;
import io.swagger.v3.oas.annotations.extensions.ExtensionProperty;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * 三种请求参数位置获取Date参数
 * 响应：使用{@link WebMvcConfig}进行全局配置
 *
 * @author WuYingBin
 * date: 2023/5/3
 */
@Slf4j
@RestController
@Tag(name = "Date Test Controller", description = "Date Test Controller",
        extensions = {@Extension(properties = {@ExtensionProperty(name = "x-order", value = "1", parseValue = true)})})
@RequestMapping("/date")
public class DateController {
    /**
     * 获取Url中Date类型参数
     * <p>
     * {@link DateTimeFormat}把Path参数按格式转换为日期类型参数
     */
    @GetMapping("/url/{date}")
    @Operation(summary = "url date")
    @ApiOperationSupport(order = 1)
    public CommonResponseDTO<Date> getUrlDate(@PathVariable(name = "date")
                                              @DateTimeFormat(pattern = DateTimeUtil.DATE_TIME_PATTERN) Date date) {
        String result = DateTimeUtil.DATE_FORMAT.format(date);
        log.info("get date from path: {}", result);
        return CommonResponseDTO.success(date);
    }

    /**
     * 获取Param中Date类型参数
     * <p>
     * {@link DateTimeFormat}把Param参数按格式转换为日期类型参数
     */
    @GetMapping("/param")
    @Operation(summary = "param date")
    @ApiOperationSupport(order = 2)
    public CommonResponseDTO<Date> getParamDate(@RequestParam(name = "date")
                                                @DateTimeFormat(pattern = DateTimeUtil.DATE_TIME_PATTERN) Date date) {
        String result = DateTimeUtil.DATE_FORMAT.format(date);
        log.info("get date from param: {}", result);
        return CommonResponseDTO.success(date);
    }

    /**
     * 获取Body中Date类型参数
     * <p>
     * {@link WebMvcConfig}中全局配置LocalDateTime序列化与反序列化的格式
     */
    @PostMapping("/body")
    @Operation(summary = "body date")
    @ApiOperationSupport(order = 3)
    public CommonResponseDTO<DateRequestDTO> getBodyDate(@RequestBody DateRequestDTO dateRequestDTO) {
        String result = DateTimeUtil.DATE_FORMAT.format(dateRequestDTO.getDate());
        log.info("get date from body: {}", result);
        return CommonResponseDTO.success(dateRequestDTO);
    }
}
