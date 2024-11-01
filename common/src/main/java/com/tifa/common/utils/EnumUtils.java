package com.tifa.common.utils;

import com.tifa.common.base.BaseEnum;
import com.tifa.common.constants.ExceptionConstants;

/**
 * 枚举工具类
 *
 * @author lihao
 * &#064;date  2024/11/1--12:49
 * @since 1.0
 */
public class EnumUtils {
    public static <T extends BaseEnum> T getEnumByCode(Class<T> enumClass, Integer code) {
        for (T each : enumClass.getEnumConstants()) {
            if (code.equals(each.getCode())) {
                return each;
            }
        }
        throw new IllegalArgumentException(ExceptionConstants.INVALID_ENUM_CODE);
    }
}
