package com.github.gelald.datetime;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author WuYingBin
 * date: 2023/5/3
 */
@Data
public class LocalDateTimeRequestDTO implements Serializable {
    private static final long serialVersionUID = 7435595395552442126L;
    private Long id;
    /**
     * {@link JsonFormat}: 针对当前类的Date类型，按格式序列化与反序列化
     * 使用{@link WebMvcConfig}更加通用
     */
    //@JsonFormat(pattern = DateTimeUtil.DATE_TIME_PATTERN, timezone = "GMT+8")
    private LocalDateTime localDateTime;
}
