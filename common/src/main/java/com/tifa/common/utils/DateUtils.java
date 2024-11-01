package com.tifa.common.utils;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 日期工具类
 *
 * @author lihao
 * @date 2024/11/1--20:25
 * @since 1.0
 */
public class DateUtils {

    /**
     * 将毫秒值转换为 "yyyy-MM-dd" 格式的日期字符串
     *
     * @param millis 毫秒值
     * @return 格式化后的日期字符串
     */
    public static String formatToDate(long millis) {
        return formatDate(millis, "yyyy-MM-dd");
    }

    /**
     * 将毫秒值转换为 "yyyy-MM-dd'T'HH:mm:ss.SSSXXX" 格式的日期字符串
     *
     * @param millis 毫秒值
     * @return 格式化后的日期字符串
     */
    public static String formatToDateTime(long millis) {
        return formatDate(millis, "yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
    }

    /**
     * 将毫秒值转换为 "MM/dd/yyyy" 格式的日期字符串
     *
     * @param millis 毫秒值
     * @return 格式化后的日期字符串
     */
    public static String formatToUSDate(long millis) {
        return formatDate(millis, "MM/dd/yyyy");
    }

    /**
     * 将毫秒值转换为 "dd-MM-yyyy" 格式的日期字符串
     *
     * @param millis 毫秒值
     * @return 格式化后的日期字符串
     */
    public static String formatToEuropeanDate(long millis) {
        return formatDate(millis, "dd-MM-yyyy");
    }

    /**
     * 将毫秒值转换为 "yyyy年MM月dd日" 格式的日期字符串
     *
     * @param millis 毫秒值
     * @return 格式化后的日期字符串
     */
    public static String formatToChineseDate(long millis) {
        return formatDate(millis, "yyyy年MM月dd日");
    }

    /**
     * 将毫秒值转换为 "EEEE, MMMM dd, yyyy" 格式的日期字符串
     *
     * @param millis 毫秒值
     * @return 格式化后的日期字符串
     */
    public static String formatToFullDate(long millis) {
        return formatDate(millis, "EEEE, MMMM dd, yyyy");
    }

    /**
     * 通用的日期格式化方法
     *
     * @param millis 毫秒值
     * @param pattern 日期格式
     * @return 格式化后的日期字符串
     */
    private static String formatDate(long millis, String pattern) {
        // 将毫秒值转换为 Instant 对象
        Instant instant = Instant.ofEpochMilli(millis);
        // 将 Instant 对象转换为 ZonedDateTime 对象
        ZonedDateTime dateTime = instant.atZone(ZoneId.systemDefault());
        // 定义日期格式化器
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        // 格式化日期
        return dateTime.format(formatter);
    }
}
