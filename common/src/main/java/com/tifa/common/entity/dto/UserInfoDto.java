package com.tifa.common.entity.dto;

import lombok.Data;

/**
 * 用户Dto
 *
 * @author lihao
 * &#064;date  2024/10/28--20:37
 * @since 1.0
 */
@Data
public class UserInfoDto {
    // 用户id
    private String id;
    // 手机号
    private String phone;
    // 用户名
    private String username;
    // 邮箱
    private String email;
    // 头像
    private String avatar;
    // 创建时间
    private Long createDate;
    // 密码
    private String password;
}
