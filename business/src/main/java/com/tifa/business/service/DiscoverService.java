package com.tifa.business.service;

import com.tifa.common.base.PageVo;

import java.util.Map;

/**
 * 发现业务层
 *
 * @author lihao
 * &#064;date  2024/11/1--16:02
 * @since 1.0
 */
public interface DiscoverService {
    Map<String, Object> listSelect(PageVo pageVo);
}
