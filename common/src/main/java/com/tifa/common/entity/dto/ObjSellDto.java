package com.tifa.common.entity.dto;

import lombok.Data;

/**
 * 模型出售信息
 *
 * @author lihao
 * &#064;date  2024/11/1--12:29
 * @since 1.0
 */
@Data
public class ObjSellDto {
    // 商单id
    private String id;
    // 模型名称
    private String model;
    // 封面
    private String cover;
    // 用户id
    private String userId;
    // 浏览量
    private Integer views;
    // 收藏量
    private Integer favorites;
    // 状态
    private Integer status;
    // 标签
    private String tags;
    // 出售规则
    private Integer sellRules;
    // 价格
    private Integer price;
    // 上架时间
    private Long shelfDate;
    // 描述
    private String description;
    // 更新时间
    private Long updateDate;
}
