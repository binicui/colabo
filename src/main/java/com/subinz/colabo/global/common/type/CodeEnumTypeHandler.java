package com.subinz.colabo.global.common.type;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * {@link CodeEnum} 인터페이스를 구현한 열거형의 문자열 타입의 코드값과 데이터베이스의 컬럼 간의 매핑을 통해
 * 코드값을 데이터베이스에 저장하거나, 데이터베이스의 코드값을 열거형으로 변환하는 작업을 수행하는 타입 핸들러.
 *
 * @param <E>   타입 핸들러의 처리 대상인 열거형
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
        return findByCode(rs.getString(columnName));
    }

    @Override
    public E getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return findByCode(rs.getString(columnIndex));
    }

    @Override
    public E getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return findByCode(cs.getString(columnIndex));
    }

    /**
     * 데이터베이스로부터 읽어온 코드값과 매치되는 열거형 상수를 반환한다.
     *
     * @param code  데이터베이스로부터 읽어온 코드값
     * @return      {@code code}와 일치하는 값이 존재할 경우 해당 값의 열거형 상수를 반환
     */
    private E findByCode(String code) {
        E[] enums = type.getEnumConstants();
        for (E e : enums) {
            if (e.getCode().equals(code)) {
                return e;
            }
        }
        throw new IllegalArgumentException("No matching enum constant for '" + code + "' in " + type.getSimpleName());
    }
}