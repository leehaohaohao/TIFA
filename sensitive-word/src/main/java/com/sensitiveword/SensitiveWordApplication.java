package com.sensitiveword;

import com.github.houbb.sensitive.word.bs.SensitiveWordBs;
import jakarta.annotation.PostConstruct;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author lihao
 */
@SpringBootApplication
@MapperScan("com.sensitiveword.mapper")
public class SensitiveWordApplication {
    @PostConstruct
    private void init() {
        //初始化敏感词库
        SensitiveWordBs.newInstance().init();
    }
    public static void main(String[] args) {
        SpringApplication.run(SensitiveWordApplication.class, args);
    }

}
