package com.tifa.common.entity.dto;

import lombok.Data;

/**
 * 模型dto
 *
 * @author lihao
 * &#064;date  2024/10/30--21:25
 * @since 1.0
 */
@Data
public class ObjDto {
    // 模型id
    private String id;
    // 图片地址
    private String img;
    // 模型名称
    private String model;
    // 模型生成时间
    private Long generateDate;
    // 用户id
    private String userId;
    // 文本生成描述
    private String text;
}
