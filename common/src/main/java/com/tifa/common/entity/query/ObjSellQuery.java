package com.tifa.common.entity.query;

import com.tifa.common.base.PageVo;
import com.tifa.common.entity.dto.ObjSellDto;
import lombok.Data;

/**
 * obj sell 查询条件
 *
 * @author lihao
 * &#064;date  2024/11/1--16:53
 * @since 1.0
 */
@Data
public class ObjSellQuery {
    private String id;
    private PageVo pageVo;
    private ObjSellDto.SellStatus sellStatus;
    private ObjSellDto.SellRules sellRules;
    private String userId;
    private Integer topPrice;
    private Integer bottomPrice;
    private String tags;
    private String orderBy;
}
