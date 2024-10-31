package com.sensitiveword.interceptor;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.digest.HMac;
import cn.hutool.crypto.digest.HmacAlgorithm;
import com.sensitiveword.entity.ApiKeys;
import com.sensitiveword.mapper.ApiKeysMapper;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * 鉴权拦截器
 *
 * @author lihao
 * &#064;date  2024/10/31--16:11
 * @since 1.0
 */
@Component
public class AuthInterceptor implements HandlerInterceptor {
    @Resource
    private ApiKeysMapper apiKeysMapper;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String accessKey = request.getHeader("AccessKey");
        String clientSignature = request.getHeader("Signature");
        String timestamp = request.getHeader("Timestamp");
        String nonce = request.getHeader("Nonce");
        // 从数据库或配置文件中获取 secretKey
        String secretKey = getSecretKey(accessKey);
        // 获取请求参数
        Map<String, String> params = new HashMap<>();
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String paramName = parameterNames.nextElement();
            params.put(paramName, request.getParameter(paramName));
        }

        // 将时间戳和随机数也加入参数
        params.put("timestamp", timestamp);
        params.put("nonce", nonce);

        // 生成服务器端签名
        String data = createDataString(params);
        String serverSignature = generateSignature(data, secretKey);

        // 验证签名
        if (serverSignature.equals(clientSignature)) {
            return true;
        } else {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }
    }

    private String getSecretKey(String accessKey) {
        ApiKeys apiKeys = apiKeysMapper.selectByAccessKey(accessKey);
        if(apiKeys == null){
            throw new RuntimeException("无效的AccessKey");
        }
        return apiKeys.getSecretKey();
    }

    private String createDataString(Map<String, String> params) {
        StringBuilder data = new StringBuilder();
        params.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .forEach(entry -> data.append(entry.getKey()).append("=").append(entry.getValue()).append("&"));
        return data.substring(0, data.length() - 1);
    }

    private String generateSignature(String data, String secretKey) {
        HMac hMac = SecureUtil.hmac(HmacAlgorithm.HmacSHA256, secretKey.getBytes());
        return hMac.digestHex(data);
    }
}
