package com.tifa.business.controller;

import com.tifa.common.entity.dto.ResponsePack;
import com.tifa.common.mapper.AuditMapper;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
    private final AuditMapper auditMapper;

    /**
     * 发现列表
     * @return
     */
    @GetMapping("/list/select")
    public ResponsePack listSelect() {
        return ResponsePack.success(auditMapper.selectById("A1851247470169407488"));
    }
}
