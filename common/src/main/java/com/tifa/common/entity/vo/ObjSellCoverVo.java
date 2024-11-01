package com.tifa.common.entity.vo;

import lombok.Data;

import java.util.Date;

/**
 * 售卖模型带封面vo
 *
 * @author lihao
 * &#064;date  2024/11/1--19:28
 * @since 1.0
 */
@Data
public class ObjSellCoverVo {
    private String id;
    private String userId;
    private String cover;
    private int views;
    private int favorites;
    private Date shelfDate;
    private String avatar;
    private String username;
}
