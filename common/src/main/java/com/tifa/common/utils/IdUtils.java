package com.tifa.common.utils;

import cn.hutool.core.util.IdUtil;
import com.tifa.common.enums.IdPrefixEnum;

/**
 * id 生成工具
 *
 * @author lihao
 * &#064;date  2024/11/1--14:18
 * @since 1.0
 */
public class IdUtils {
    /**
     * 生成id
     * @param prefix 前缀
     * @return id
     */
    private static String generateId(String prefix) {
        long id = IdUtil.getSnowflakeNextId();
        return prefix+id;
    }
    /**
     * 生成用户id
     * @return id
     */
    public static String generateUserId() {
        return generateId(IdPrefixEnum.USER.getPrefix());
    }

    /**
     * 生成对象id
     * @return id
     */
    public static String generateObjId() {
        return generateId(IdPrefixEnum.OBJ.getPrefix());
    }

    /**
     * 生成审核id
     * @return id
     */
    public static String generateAuditId() {
        return generateId(IdPrefixEnum.AUDIT.getPrefix());
    }
    /**
     * 生成模型id
     * @return id
     */
    public static String generateModelId() {
        return generateId(IdPrefixEnum.MODEL.getPrefix());
    }
    public static String generateSellId() {
        return generateId(IdPrefixEnum.SELL.getPrefix());
    }
}
