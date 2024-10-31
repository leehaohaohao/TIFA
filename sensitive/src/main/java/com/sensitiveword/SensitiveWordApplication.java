package com.sensitiveword;

import com.github.houbb.sensitive.word.bs.SensitiveWordBs;
import com.github.houbb.sensitive.word.support.ignore.SensitiveWordCharIgnores;
import com.github.houbb.sensitive.word.support.resultcondition.WordResultConditions;
import com.github.houbb.sensitive.word.support.tag.WordTags;
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
    public static void main(String[] args) {
        SpringApplication.run(SensitiveWordApplication.class, args);
    }

}
