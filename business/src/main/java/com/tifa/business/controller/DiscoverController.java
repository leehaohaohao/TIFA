package com.tifa.business.controller;

import com.tifa.common.entity.dto.ResponsePack;
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
public class DiscoverController {
    @GetMapping("/list/select")
    public ResponsePack listSelect() {
        return ResponsePack.success("success");
    }
}
