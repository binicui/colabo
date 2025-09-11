package com.subinz.colabo.global.common.type;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * {@link CodeEnum}을 구현한 열거형의 문자열 타입의 코드값과 데이터베이스의 컬럼 간의 매핑을 통해
 * 코드값의 데이터베이스 저장 처리 및 변환 작업 등을 수행하는 타입 핸들러 클래스
 *
 *
 * @param <E>   타입 핸들러의 처리 대상이 되는 열거형
 * @see         CodeEnum
 */

@MappedTypes(CodeEnum.class)
public class CodeEnumTypeHandler<E extends Enum<E> & CodeEnum> extends BaseTypeHandler<E> {

    private final Class<E> type;

    public CodeEnumTypeHandler(Class<E> type) {
        if (type == null) {
            throw new IllegalArgumentException("Type argument cannot be null.");
        }
        this.type = type;
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, E parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter.getCode());
    }

    @Override
    public E getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return codeToEnum(rs.getString(columnName));
    }

    @Override
    public E getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return codeToEnum(rs.getString(columnIndex));
    }

    @Override
    public E getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return codeToEnum(cs.getString(columnIndex));
    }

    private E codeToEnum(String code) {
        E[] enums = type.getEnumConstants();
        for (E e : enums) {
            if (e.getCode().equals(code)) {
                return e;
            }
        }
        throw new IllegalArgumentException("No matching enum constant for " + code + " in " + type.getSimpleName());
    }
}