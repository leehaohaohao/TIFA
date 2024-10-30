package com.tifa.common.entity.vo;

import lombok.Builder;
import lombok.Data;

/**
 * 验证码返回值
 *
 * @author lihao
 * &#064;date  2024/10/30--19:38
 * @since 1.0
 */
@Data
public class CodeVo {

    //验证码id
    private String id;
    //验证码图片
    private String base64;
}
