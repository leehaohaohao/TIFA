package com.tifa.common.base;

import com.tifa.common.utils.EnumUtils;
/**
 * 基础枚举类
 *
 * @author lihao
 * &#064;date  2024/11/1--12:45
 * @since 1.0
 */
public abstract class BaseDto<T extends BaseEnum> {
    private T type;
    public void setType(Integer code,Class<T> enumClass){
        this.type = EnumUtils.getEnumByCode(enumClass,code);
    }
    public T getType(){
        return type;
    }
}
