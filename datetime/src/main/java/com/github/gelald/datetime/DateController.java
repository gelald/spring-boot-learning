package com.github.gelald.datetime;

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
@RequestMapping("/date")
public class DateController {
    /**
     * 获取Url中Date类型参数
     * <p>
     * {@link DateTimeFormat}: 把Param参数按格式转换为日期类型参数
     */
    @GetMapping("/url/{date}")
    public CommonResponseDTO<Date> getUrlDate(@PathVariable(name = "date")
                                              @DateTimeFormat(pattern = DateTimeUtil.DATE_TIME_PATTERN) Date date) {
        String result = DateTimeUtil.DATE_FORMAT.format(date);
        log.info("date: {}", result);
        return CommonResponseDTO.success(date);
    }

    /**
     * 获取Param中Date类型参数
     * <p>
     * {@link DateTimeFormat}把Param参数按格式转换为日期类型参数
     */
    @GetMapping("/param")
    public CommonResponseDTO<Date> getParamDate(@RequestParam(name = "date")
                                                @DateTimeFormat(pattern = DateTimeUtil.DATE_TIME_PATTERN) Date date) {
        String result = DateTimeUtil.DATE_FORMAT.format(date);
        log.info("date: {}", result);
        return CommonResponseDTO.success(date);
    }

    /**
     * 获取Body中Date类型参数
     * <p>
     * {@link WebMvcConfig}中全局配置LocalDateTime序列化与反序列化的格式
     */
    @PostMapping("/body")
    public CommonResponseDTO<DateRequestDTO> getBodyDate(@RequestBody DateRequestDTO dateRequestDTO) {
        String result = DateTimeUtil.DATE_FORMAT.format(dateRequestDTO.getDate());
        log.info("date: {}", result);
        return CommonResponseDTO.success(dateRequestDTO);
    }
}
