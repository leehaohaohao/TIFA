package com.tifa.common.handler.mybatis;

import com.tifa.common.base.BaseEnum;
import org.apache.ibatis.type.TypeHandlerRegistry;

/**
 * 枚举类型处理器工厂
 *
 * @author lihao
 * &#064;date  2024/11/1--13:02
 * @since 1.0
 */
public class EnumTypeHandlerFactory {
    public static <E extends Enum<E> & BaseEnum> void registerEnumTypeHandlers(TypeHandlerRegistry registry, Class<E>[] enumClasses) {
        for (Class<E> enumClass : enumClasses) {
            registry.register(enumClass, new BaseEnumTypeHandler<>(enumClass));
        }
    }
}

