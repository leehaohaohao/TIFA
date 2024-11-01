package com.tifa.common.handler.mybatis;

import com.tifa.common.base.BaseEnum;
import com.tifa.common.constants.ExceptionConstants;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * enum 类型转换器
 *
 * @author lihao
 * &#064;date  2024/11/1--12:54
 * @since 1.0
 */
public class BaseEnumTypeHandler <E extends Enum<E> & BaseEnum> extends BaseTypeHandler<E> {
    private Class<E> type;
    public BaseEnumTypeHandler() {}
    public BaseEnumTypeHandler(Class<E> type) {
        if (type == null) {
            throw new IllegalArgumentException(ExceptionConstants.INVALID_PARAMETER);
        }
        this.type = type;
    }
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, E parameter, JdbcType jdbcType) throws SQLException {
        ps.setInt(i, parameter.getCode());
    }

    @Override
    public E getNullableResult(ResultSet rs, String columnName) throws SQLException {
        int code = rs.getInt(columnName);
        return getEnumByCode(code);
    }

    @Override
    public E getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        int code = rs.getInt(columnIndex);
        return getEnumByCode(code);
    }

    @Override
    public E getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        int code = cs.getInt(columnIndex);
        return getEnumByCode(code);
    }
    private E getEnumByCode(int code) {
        for (E enumConstant : type.getEnumConstants()) {
            if (enumConstant.getCode().equals(code)) {
                return enumConstant;
            }
        }
        throw new IllegalArgumentException(ExceptionConstants.INVALID_ENUM_CODE);
    }
}
