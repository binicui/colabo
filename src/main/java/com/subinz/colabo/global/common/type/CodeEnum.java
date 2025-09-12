package com.subinz.colabo.global.common.type;

/**
 * 데이터베이스의 컬럼과의 매핑읕 통해 저장될 코드값을 가진 열거형을 일관적으로 구현하기 위한 인터페이스
 *
 * @see CodeEnumTypeHandler
 */

public interface CodeEnum {

    /**
     * 데이터베이스 작업의 대상이 되는 열거형의 문자열 타입의 코드값을 반환한다.
     */
    String getCode();
}