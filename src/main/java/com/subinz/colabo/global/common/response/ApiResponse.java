package com.subinz.colabo.global.common.response;

import com.subinz.colabo.global.common.response.base.BaseResponse;
import com.subinz.colabo.global.common.response.base.ResponseType;
import lombok.Getter;

/**
 * 성공 응답 클래스
 * 
 * @param <T>   응답 결과 데이터 타입
 */

@Getter
public class ApiResponse<T> extends BaseResponse {

    private final T data;

    private ApiResponse(int code, String message, T data) {
        super(code, message);
        this.data = data;
    }

    /**
     * 성공 응답을 반환한다.
     * 
     * @param data  응답 결과 데이터
     * @return      {@link ApiResponse} 객체
     * @param <T>   응답 결과 데이터 타입
     */
    public static<T> ApiResponse<T> ok(T data) {
        return new ApiResponse<>(ResponseType.OK.getCode(), ResponseType.OK.getMessage(), data);
    }
}