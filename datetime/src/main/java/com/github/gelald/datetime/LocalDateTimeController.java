package com.github.gelald.datetime;

import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * 三种请求参数位置获取LocalDateTime参数
 * 响应：使用{@link WebMvcConfig}进行全局配置
 *
 * @author WuYingBin
 * date: 2023/5/3
 */
@Slf4j
@RestController
@RequestMapping("/local-date-time")
public class LocalDateTimeController {
    /**
     * 获取Url中的LocalDateTime参数
     * <p>
     * {@link DateTimeFormat}: 把Param参数按格式转换为日期类型参数
     */
    @GetMapping("/url/{localDateTime}")
    public CommonResponseDTO<LocalDateTime> getUrlLocalDateTime(@PathVariable("localDateTime")
                                                                @DateTimeFormat(pattern = DateTimeUtil.DATE_TIME_PATTERN) LocalDateTime localDateTime) {
        String result = DateTimeUtil.DATE_TIME_FORMATTER.format(localDateTime);
        log.info("localDateTime: {}", result);
        return CommonResponseDTO.success(localDateTime);
    }

    /**
     * 获取Param中的LocalDateTime参数
     * <p>
     * {@link DateTimeFormat}: 把Param参数按格式转换为日期类型参数
     */
    @GetMapping("/param")
    public CommonResponseDTO<LocalDateTime> getParamLocalDateTime(@RequestParam("localDateTime")
                                                                  @DateTimeFormat(pattern = DateTimeUtil.DATE_TIME_PATTERN) LocalDateTime localDateTime) {
        String result = DateTimeUtil.DATE_TIME_FORMATTER.format(localDateTime);
        log.info("localDateTime: {}", result);
        return CommonResponseDTO.success(localDateTime);
    }

    /**
     * 获取Body中的LocalDateTime参数
     * <p>
     * {@link WebMvcConfig}中全局配置LocalDateTime序列化与反序列化的格式
     */
    @PostMapping("/body")
    public CommonResponseDTO<LocalDateTimeRequestDTO> getBodyLocalDateTime(@RequestBody LocalDateTimeRequestDTO localDateTimeRequestDTO) {
        String result = DateTimeUtil.DATE_TIME_FORMATTER.format(localDateTimeRequestDTO.getLocalDateTime());
        log.info("localDateTime: {}", result);
        return CommonResponseDTO.success(localDateTimeRequestDTO);
    }
}
