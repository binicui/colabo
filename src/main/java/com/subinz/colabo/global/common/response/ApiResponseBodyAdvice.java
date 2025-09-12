package com.subinz.colabo.global.common.response;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

/**
 * {@code @RestController} 어노테이션이 선언된 클래스의 응답 본문을 가로채 {@link ApiResponse}를 통해 래핑 후 클라이언트에게 반환하는 클래스
 */

@RestControllerAdvice(basePackages = "com.subinz.colabo.domain")
public class ApiResponseBodyAdvice implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return ResponseEntity.class.isAssignableFrom(returnType.getParameterType());
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
                                  Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request,
                                  ServerHttpResponse response) {
        /* ResponseEntity<?> 정보 가져오기 */
        Type genericType = returnType.getGenericParameterType();
        /* ResponseEntity<> 안에 타입 정보 가져오기 */
        Type actualType = (genericType instanceof ParameterizedType)
                ? ((ParameterizedType) genericType).getActualTypeArguments()[0] : genericType;

        return ApiResponse.ok((body == null) ? getEmptyPayload(actualType) : body);
    }

    /**
     * 응답 결과 데이터의 구조 유형에 따라 빈 리스트 또는 빈 맵을 반환한다.
     *
     * @param actualType    {@link ResponseEntity}의 데이터 타입
     * @return              {@code actualType}이 {@link List}일 경우 빈 리스트를, 아닐 경우 빈 맵을 반환
     */
    private Object getEmptyPayload(Type actualType) {
        Type rawType = (actualType instanceof ParameterizedType) ? ((ParameterizedType) actualType).getRawType() : actualType;
        if (rawType instanceof Class<?> clazz) {
            if (List.class.isAssignableFrom(clazz)) {
                return Collections.emptyList();
            }
        }
        return Collections.emptyMap();
    }
}