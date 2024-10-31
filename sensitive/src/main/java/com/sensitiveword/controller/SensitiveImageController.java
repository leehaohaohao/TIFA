package com.sensitiveword.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 敏感图片相关接口
 *
 * @author lihao
 * &#064;date  2024/10/31--20:24
 * @since 1.0
 */
@RestController
@RequestMapping("/image")
public class SensitiveImageController {
    @PostMapping("/check")
    public ResponseEntity checkImage() {
        return null;
    }
}
