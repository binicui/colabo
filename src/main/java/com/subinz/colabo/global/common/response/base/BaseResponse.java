package com.subinz.colabo.global.common.response.base;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class BaseResponse {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private final LocalDateTime responseAt;

    private final int code;

    private final String message;

    public BaseResponse(int code, String message) {
        this.responseAt = LocalDateTime.now();
        this.code = code;
        this.message = message;
    }
}