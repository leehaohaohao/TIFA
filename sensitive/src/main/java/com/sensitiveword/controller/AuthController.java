package com.sensitiveword.controller;

import cn.hutool.core.util.IdUtil;
import cn.hutool.crypto.asymmetric.RSA;
import com.lihao.demo.current_limiting.base.CurrentLimiting;
import com.sensitiveword.entity.ApiKeys;
import com.sensitiveword.mapper.ApiKeysMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 权限控制器
 *
 * @author lihao
 * &#064;date  2024/10/31--16:16
 * @since 1.0
 */
@RestController
@RequestMapping("/auth")
@CrossOrigin
@AllArgsConstructor
public class AuthController {
    private final ApiKeysMapper apiKeysMapper;
    @PostMapping("/create")
    @CurrentLimiting(capacity = 20, rate = 10)
    public ResponseEntity<ApiKeys> application(String phone){
        ApiKeys apiKeys = apiKeysMapper.selectByPhone(phone);
        if(apiKeys == null){
            RSA rsa = new RSA();
            String accessKey = rsa.getPrivateKeyBase64();
            String secretKey = rsa.getPublicKeyBase64();
            apiKeys = new ApiKeys();
            apiKeys.setId(IdUtil.getSnowflakeNextId());
            apiKeys.setCreateDate(System.currentTimeMillis());
            apiKeys.setPhone(phone);
            apiKeys.setAccessKey(accessKey);
            apiKeys.setSecretKey(secretKey);
            apiKeysMapper.insert(apiKeys);
        }
        apiKeys.setPhone(null);
        apiKeys.setStatus(null);
        apiKeys.setCreateDate(null);
        apiKeys.setId(null);
        return ResponseEntity.ok(apiKeys);
    }
}
