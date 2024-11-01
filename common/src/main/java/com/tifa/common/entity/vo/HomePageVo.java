package com.tifa.common.entity.vo;

import lombok.Data;

/**
 * 首页轮播图展示
 *
 * @author lihao
 * &#064;date  2024/11/1--16:13
 * @since 1.0
 */
@Data
public class HomePageVo {
    private String id;
    private String title;
    private String subTitle;
    private String[] images;
    private String path;
}
