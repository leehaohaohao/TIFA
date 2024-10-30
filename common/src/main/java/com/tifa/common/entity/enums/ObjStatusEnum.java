package com.tifa.common.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 模型状态枚举
 *
 * @author lihao
 * &#064;date  2024/10/30--21:33
 * @since 1.0
 */
@Getter
@AllArgsConstructor
public enum ObjStatusEnum {
    NORMAL(0, "正常"),
    SELLING(1, "在售"),
    SOLD_OUT(2, "售罄"),
    AFTER_SALE(3, "售后"),
    DELETED(4, "已删除");
    // 状态码
    private final Integer status;
    // 状态描述
    private final String msg;
    public static ObjStatusEnum getEnum(Integer status) {
        for (ObjStatusEnum objStatusEnum : ObjStatusEnum.values()) {
            if (objStatusEnum.getStatus().equals(status)) {
                return objStatusEnum;
            }
        }
        return null;
    }
}
