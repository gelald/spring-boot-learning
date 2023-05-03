package com.github.gelald.datetime;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * @author WuYingBin
 * date: 2023/5/3
 */
public class DateTimeUtil {
    /**
     * 通用的日期时间格式
     */
    public static final String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    /**
     * 格式化Date类型的工具
     */
    public static final DateFormat DATE_FORMAT = new SimpleDateFormat(DATE_TIME_PATTERN, Locale.CHINA);
    /**
     * 格式化LocalDateTime类型的工具
     */
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(DATE_TIME_PATTERN, Locale.CHINA);
}
