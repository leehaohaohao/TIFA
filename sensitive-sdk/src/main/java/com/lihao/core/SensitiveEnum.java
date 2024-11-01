package com.lihao.core;

/**
 * 敏感服务枚举类
 *
 * @author lihao
 * &#064;date  2024/10/31--20:48
 * @since 1.0
 */
public enum SensitiveEnum {
    TEXT_CHECK(1, "/cover/check"),
    IMAGE_CHECK(2, "/image/check"),
    AUDIO(3, "音频"),
    VIDEO(4, "视频"),
    DOCUMENT(5, "文档"),
    OTHER(6, "其他");

    private final Integer code;
    private final String msg;

    SensitiveEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

   public String getMsg() {
        return msg;
   }
}
