package com.github.gelald.datetime;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * @author WuYingBin
 * date: 2023/5/3
 */
@Data
@Builder
public class CommonResponseDTO<T> implements Serializable {
    private static final long serialVersionUID = 34242552442126L;
    private Integer code;
    private String message;
    private T content;

    public static <T> CommonResponseDTO<T> success(T content) {
        return new CommonResponseDTOBuilder<T>()
                .code(200)
                .message("success")
                .content(content)
                .build();
    }
}
