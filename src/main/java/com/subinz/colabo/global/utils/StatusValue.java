package com.subinz.colabo.global.utils;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 프로젝트 전반에서 사용되는 상태값에 대한 서로 다른 타입의 데이터들을 상응되는 의미 별로 정의 및 관리하기 위한 열거형
 */

@RequiredArgsConstructor
@Getter
public enum StatusValue {

    YES (1, "Y", true),
    NO (0, "N", false);

    private final int number;

    private final String character;

    private final boolean bool;
}