package com.tifa.common.entity.po;

import lombok.Data;

/**
 * 用户上下文信息
 *
 * @author lihao
 * &#064;date  2024/10/29--19:03
 * @since 1.0
 */
@Data
public class UserContext {
    private Long id;
    private String ipv4;
    private String ipv6;
    private String proxyIp;
}