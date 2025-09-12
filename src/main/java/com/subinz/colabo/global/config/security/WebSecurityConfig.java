package com.subinz.colabo.global.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    /**
     * 비밀번호 암호화 객체를 스프링 빈 등록한다.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * HTTP 요청에 대한 인증 및 인가 등의 보안 관련 정책을 설정한다.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.httpBasic(basic -> basic.disable())    // HTTP 기본 인증 비활성화
            .formLogin(form -> form.disable())     // 폼 기반 로그인 방식 비활성화
            .csrf(csrf -> csrf.disable());              // CSRF 공격 방어 기능 비활성화
        /* H2 콘솔 접속을 위한 X-Frame-Option 헤더 설정 */
        http.headers(header -> header
                .frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin));
        /* HTTP 요청 인가 설정 */
        http.authorizeHttpRequests(auth -> auth
                .requestMatchers(SecurityConstants.PUBLIC_REQUEST_MATCHERS).permitAll()
                .anyRequest().authenticated());
        /* 세션 관리 정책 설정 */
        http.sessionManagement(session -> session
                .maximumSessions(1)                 // 최대 허용 가능한 세션 수 설정 (-1 : 무제한)
                .maxSessionsPreventsLogin(false)    // 동시 로그인 차단 (false : 기존 세션 만료, true : 인증 실패 처리)
                .expiredUrl("/"));                  // 세션 만료 시 이동할 페이지 요청 URL 지정
        return http.build();
    }

    /**
     * 정적 자원 요청을 시큐리티 필터 적용에서 제외한다.
     */
    @Bean
    public WebSecurityCustomizer securityCustomizer() {
        return (web) -> web.ignoring().requestMatchers(SecurityConstants.RESOURCE_MATCHERS);
    }
}