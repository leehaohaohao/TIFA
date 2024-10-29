package com.tifa.common.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 系统配置
 *
 * @author lihao
 * &#064;date  2024/10/29--20:13
 * @since 1.0
 */
@Component
@Data
public class SysConfig {
    @Value("${default-username}")
    private String defaultUsername;
}
