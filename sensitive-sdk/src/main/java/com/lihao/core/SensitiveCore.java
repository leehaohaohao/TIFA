package com.lihao.core;

import okhttp3.OkHttpClient;

/**
 * 敏感服务sdk核心类
 *
 * @author lihao
 * &#064;date  2024/10/31--20:42
 * @since 1.0
 */
public class SensitiveCore {
    private final OkHttpClient httpClient;
    private final String accessKey;
    private final String secretKey;

    public SensitiveCore(String accessKey, String secretKey) {
        this.httpClient = new OkHttpClient();
        this.accessKey = accessKey;
        this.secretKey = secretKey;
    }
}
