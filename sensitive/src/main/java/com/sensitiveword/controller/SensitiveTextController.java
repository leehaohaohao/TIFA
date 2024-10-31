package com.sensitiveword.controller;

import com.github.houbb.sensitive.word.bs.SensitiveWordBs;
import com.github.houbb.sensitive.word.core.SensitiveWordHelper;
import com.sensitiveword.config.SensitiveConfig;
import jakarta.annotation.PostConstruct;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 敏感词文本处理
 *
 * @author lihao
 * &#064;date  2024/10/31--15:57
 * @since 1.0
 */
@RequestMapping("/text")
@RestController
@CrossOrigin
public class SensitiveTextController {
    private SensitiveWordBs bs;
    @PostConstruct
    private void init()
    {
        //初始化敏感词库
        bs= SensitiveConfig.getInstance();
    }
    /**
     * 检查文本是否包含敏感词
     * @param map 文本
     * @return
     */
    @PostMapping("/check")
    public ResponseEntity checkText(@RequestBody Map<String,String> map)
    {
        String text = map.get("text");
        // 检查敏感词
        String result = bs.replace(text);
        return ResponseEntity.ok(result);
    }
}
