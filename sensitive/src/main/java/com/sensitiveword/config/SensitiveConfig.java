package com.sensitiveword.config;

import com.github.houbb.sensitive.word.bs.SensitiveWordBs;
import com.github.houbb.sensitive.word.core.SensitiveWordHelper;
import com.github.houbb.sensitive.word.support.ignore.SensitiveWordCharIgnores;
import com.github.houbb.sensitive.word.support.resultcondition.WordResultConditions;
import com.github.houbb.sensitive.word.support.tag.WordTags;

/**
 * 敏感词配置
 *
 * @author lihao
 * &#064;date  2024/10/31--20:05
 * @since 1.0
 */
public class SensitiveConfig {
    private static final SensitiveWordBs SENSITIVE_WORD_BS;
    static {
        //初始化敏感词库
        SENSITIVE_WORD_BS = SensitiveWordBs.newInstance().ignoreCase(true)
                .ignoreWidth(true)
                .ignoreNumStyle(true)
                .ignoreChineseStyle(true)
                .ignoreEnglishStyle(true)
                .ignoreRepeat(true)
                .enableNumCheck(true)
                .enableEmailCheck(true)
                .enableUrlCheck(true)
                .enableIpv4Check(true)
                .enableWordCheck(true)
                .numCheckLen(8)
                .wordTag(WordTags.none())
                .charIgnore(SensitiveWordCharIgnores.defaults())
                .wordResultCondition(WordResultConditions.alwaysTrue())
                .init();
    }
    public static SensitiveWordBs getInstance(){
        return SENSITIVE_WORD_BS;
    }

}
