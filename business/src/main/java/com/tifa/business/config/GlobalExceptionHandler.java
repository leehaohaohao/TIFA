package com.tifa.business.config;

import com.tifa.common.base.ResponsePack;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理
 *
 * @author lihao
 * &#064;date  2024/10/29--19:25
 * @since 1.0
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    public ResponsePack<?> handleException(Exception e) {
        log.error("全局异常捕获:", e);
        return ResponsePack.fail(e.getMessage());
    }
}
