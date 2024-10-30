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
    private Long id;
    // 图片地址
    private String img;
    // 上传时间
    private Long uploadDate;
    // 模型名称
    private String model;
    // 模型生成时间
    private Long generateDate;
    // 用户id
    private Long userId;
    // 标签
    private String tags;
    // 状态
    private Integer status;
    // 浏览量
    private Integer views;
    // 收藏量
    private Integer favorites;
}
