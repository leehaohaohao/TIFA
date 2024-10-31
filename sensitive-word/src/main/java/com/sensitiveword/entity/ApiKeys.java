package com.sensitiveword.entity;

import lombok.Data;

/**
 * api key
 *
 * @author lihao
 * &#064;date  2024/10/31--16:08
 * @since 1.0
 */
@Data
public class ApiKeys {
    private Long id;
    private String accessKey;
    private String secretKey;
    private Long createDate;
    private Integer status;
    private String phone;
}
