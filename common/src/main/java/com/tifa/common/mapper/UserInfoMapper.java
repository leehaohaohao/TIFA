package com.tifa.common.mapper;

import com.tifa.common.entity.dto.UserInfoDto;
import org.apache.ibatis.annotations.Param;

/**
 * 用户表
 *
 * @author lihao
 * &#064;date  2024/10/28--20:57
 * @since 1.0
 */
public interface UserInfoMapper {
    UserInfoDto selectUserById(Long id);
    UserInfoDto selectUserByEmail(String email);
    Integer insertUser(UserInfoDto userInfoDto);
}
