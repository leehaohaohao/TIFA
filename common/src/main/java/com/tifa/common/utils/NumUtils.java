package com.tifa.common.utils;

/**
 * 数字工具类
 *
 * @author lihao
 * &#064;date  2024/10/29--20:09
 * @since 1.0
 */
public class NumUtils {
    public static long getRandomNum(int length){
        return (long) (Math.random() * Math.pow(10, length));
    }
}
