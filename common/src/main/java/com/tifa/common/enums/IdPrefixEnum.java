package com.tifa.common.enums;

import lombok.Getter;

/**
 * id 前缀枚举
 *
 * @author lihao
 * &#064;date  2024/11/1--14:17
 * @since 1.0
 */
@Getter
public enum IdPrefixEnum {
    USER("U"),
    OBJ("O"),
    MODEL("M"),
    AUDIT("A"),
    SELL("S"),
    ;

    private final String prefix;
    IdPrefixEnum(String prefix) {
        this.prefix = prefix;
    }
}
