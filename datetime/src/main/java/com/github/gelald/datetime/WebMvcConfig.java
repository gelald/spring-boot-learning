package com.github.gelald.datetime;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.DateDeserializers;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * jackson关于日期类型数据的序列化与反序列化配置
 *
 * @author WuYingBin
 * date: 2023/5/3
 */
@Slf4j
@Configuration
public class WebMvcConfig {
    @Bean
    @Primary
    public ObjectMapper customObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        //序列化，把日期类型数据转换成json字符串
        javaTimeModule.addSerializer(Date.class, new DateSerializer(false, DateTimeUtil.DATE_FORMAT));
        javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(DateTimeUtil.DATE_TIME_PATTERN)));
        //反序列化，把json字符串转换成日期类型数据
        javaTimeModule.addDeserializer(Date.class, new JsonDeserializer<>() {
            @Override
            public Date deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
                if (p.hasToken(JsonToken.VALUE_STRING)) {
                    String dateString = p.getText();
                    try {
                        return DateTimeUtil.DATE_FORMAT.parse(dateString);
                    } catch (ParseException parseException) {
                        log.error("Date类型数据反序列化失败");
                        return null;
                    }
                } else {
                    log.error("Date类型数据反序列化失败");
                    return null;
                }
            }
        });
        javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern(DateTimeUtil.DATE_TIME_PATTERN)));
        //注册时间模块
        objectMapper.registerModule(javaTimeModule);
        return objectMapper;
    }
}
