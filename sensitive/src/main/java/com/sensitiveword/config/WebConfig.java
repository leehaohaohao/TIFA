package com.sensitiveword.config;

import com.sensitiveword.interceptor.AuthInterceptor;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * web配置类
 *
 * @author lihao
 * &#064;date  2024/10/31--17:00
 * @since 1.0
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    //配置拦截器
    @Resource
    private AuthInterceptor authInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authInterceptor)
                .excludePathPatterns("/auth/create")
                .excludePathPatterns("/static/**")
                .excludePathPatterns("/resources/**")
                .excludePathPatterns("/**/*.html")
                .excludePathPatterns("/**/*.css")
                .excludePathPatterns("/**/*.js");
    }
}
