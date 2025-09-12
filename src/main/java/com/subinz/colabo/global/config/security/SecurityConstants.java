package com.subinz.colabo.global.config.security;

/**
 * 스프링 시큐리티 설정과 관련된 상수들을 정의한 상수 클래스
 */

public final class SecurityConstants {

    /**
     * 스프링 시큐리티 필터 적용에서 제외시킬 리소스 요청 URL
     */
    public static final String[] RESOURCE_MATCHERS = {
            "/favicon.ico",
            "/css/**",
            "/js/**",
            "/images/**",
            "/fonts/**",
            "/h2-console/**"
    };

    /**
     * 별도의 인증 요구 없이 액세스를 허용할 요청 URL
     */
    public static final String[] PUBLIC_REQUEST_MATCHERS = {
            /* 화면 요청 URL */
            "/",
            "/sign-up",
            /* API 요청 URL */
            "/api/v1/login",
            "/api/v1/logout",
            "/api/v1/user/exists/**",
            "/api/v1/user"
    };

    private SecurityConstants() { }
}