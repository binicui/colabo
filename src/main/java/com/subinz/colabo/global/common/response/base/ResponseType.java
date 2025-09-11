package com.subinz.colabo.global.common.response.base;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

/**
 * API 응답의 본문 구성에 필요한 정보들을 성공 / 에러 별로 정의한 열거형.
 */

@RequiredArgsConstructor
@Getter
public enum ResponseType {

    /**
     * 성공
     */
    OK (HttpStatus.OK, 100000, "요청이 정상적으로 처리되었습니다."),

    /**
     * 에러
     */
    /* 공통 */
    INVALID_PARAMETER_VALUE (HttpStatus.BAD_REQUEST, -800101, "요청 파라미터의 값이 잘못되었습니다."),
    INVALID_PARAMETER_TYPE (HttpStatus.BAD_REQUEST, -800102, "잘못된 타입의 요청 파라미터입니다."),
    UNSUPPORTED_METHOD (HttpStatus.METHOD_NOT_ALLOWED, -805100, "지원하지 않는 HTTP 메소드 요청입니다."),
    INTERNAL_SERVER_ERROR (HttpStatus.INTERNAL_SERVER_ERROR, -999999, "내부 서버에 에러가 발생했습니다."),

    /* 사용자 */
    USER_NOT_FOUND (HttpStatus.NOT_FOUND, -804210, "요청하신 사용자 정보를 찾을 수 없습니다."),
    REQUIRED_LOGIN (HttpStatus.UNAUTHORIZED, -801310, "로그인이 필요한 서비스입니다."),
    INVALID_CREDENTIALS (HttpStatus.UNAUTHORIZED, -801311, "아이디 또는 비밀번호가 일치하지 않습니다."),
    ACCESS_DENIED (HttpStatus.FORBIDDEN, -803310, "요청하신 자원에 접근할 권한이 없습니다.")
    ;

    private final HttpStatus status;

    private final int code;

    private final String message;
}