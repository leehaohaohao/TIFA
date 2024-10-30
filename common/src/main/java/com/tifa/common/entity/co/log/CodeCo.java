package com.tifa.common.entity.co.log;

import lombok.Data;

/**
 * 验证码实体类
 *
 * @author lihao
 * &#064;date  2024/10/30--19:24
 * @since 1.0
 */
@Data
public class CodeCo {
    // 验证码id
    private String id;
    // 验证码
    private String code;
    // 创建时间
    private Long createDate;
}
