package com.subinz.colabo.global.common.response.base;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

/**
 * API 응답 본문 구성에 필요한 정보들을 유형별로 정의한 열거형
 */

@RequiredArgsConstructor
@Getter
public enum ResponseType {

    /**
     * 성공 응답
     */
    OK (100000, "요청이 정상적으로 처리되었습니다.", HttpStatus.OK)
    ;

    private final int code;

    private final String message;

    private final HttpStatus status;
}