package com.sensitiveword.mapper;

import com.sensitiveword.entity.ApiKeys;
import org.apache.ibatis.annotations.Mapper;

/**
 * api keys mapper
 *
 * @author lihao
 * &#064;date  2024/10/31--16:10
 * @since 1.0
 */
@Mapper
public interface ApiKeysMapper {
    Integer insert(ApiKeys apiKeys);
    ApiKeys selectByPhone(String phone);
    ApiKeys selectByAccessKey(String accessKey);
}
