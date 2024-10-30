package com.tifa.common.entity.co.log;

import lombok.Data;

/**
 * 登陆实体类
 *
 * @author lihao
 * &#064;date  2024/10/29--19:12
 * @since 1.0
 */
@Data
public class LoginCo {
    //邮箱
    private String email;
    //密码
    private String password;
    //验证码
    private String code;
    //验证码id
    private String codeId;
}
