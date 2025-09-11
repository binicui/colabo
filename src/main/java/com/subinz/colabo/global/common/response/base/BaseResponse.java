package com.subinz.colabo.global.common.response.base;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * API 응답의 공통 필드들을 정의하는 기본 응답 클래스
 */

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