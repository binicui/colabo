package com.subinz.colabo.global.utils;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 프로젝트 전반에서 사용되는 상태값 처리를 처리하기 위해 서로 다른 타입의 데이터들을 상응되는 의미별로 정의 및 관리를 위한 열거형
 */

@RequiredArgsConstructor
@Getter
public enum StatusValue {

    YES (1, "Y", true),
    NO (0, "N", false);

    private final int number;

    private final String character;

    private final boolean bool;

    /**
     * 전달된 정수를 논리형의 상태값으로 변환한다.
     *
     * @param value 논리형 상태값으로 변환할 대상
     * @return      {@code value}가 {@code 1}일 경우 {@code true}를, {@code 0}일 경우 {@code false}를 반환
     */
    public static boolean toBoolean(int value) {
        for (StatusValue e : values()) {
            if (e.getNumber() == value) {
                return e.isBool();
            }
        }
        throw new IllegalArgumentException("Cannot convert " + value + " to boolean type status.");
    }
}