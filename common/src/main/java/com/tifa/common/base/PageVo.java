package com.tifa.common.base;

import lombok.Data;

/**
 * 分页
 *
 * @author lihao
 * &#064;date  2024/11/1--16:03
 * @since 1.0
 */
@Data
public class PageVo {
    private Integer pageNum;
    private Integer pageStart;
    public Integer getPageStart() {
        if(pageNum==null || pageSize==null){
            return null;
        }
        this.pageStart = (pageNum-1)*pageSize;
        return this.pageStart;
    }
    private Integer pageSize;
    private String orderBy;
    private long total;
    private int pages;
    private boolean hasNext;
    private boolean hasPrevious;
}
