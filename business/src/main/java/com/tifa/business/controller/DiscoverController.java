package com.tifa.business.controller;

import com.tifa.business.service.DiscoverService;
import com.tifa.common.base.BusinessException;
import com.tifa.common.base.PageVo;
import com.tifa.common.base.ResponsePack;
import com.tifa.common.constants.ExceptionConstants;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * 发现页面控制器
 *
 * @author lihao
 * &#064;date  2024/10/30--20:50
 * @since 1.0
 */
@RestController
@RequestMapping("/discover")
@AllArgsConstructor
public class DiscoverController {
    private final DiscoverService discoverService;

    /**
     * 发现列表
     * @param pageVo
     * @return
     */
    @PostMapping("/list/select")
    public ResponsePack listSelect(@RequestBody PageVo pageVo) throws BusinessException {
        if(pageVo.getPageNum() == null || pageVo.getPageSize() == null){
            throw new BusinessException(ExceptionConstants.INVALID_PARAMETER);
        }
        return ResponsePack.success(discoverService.listSelect(pageVo));
    }
}
