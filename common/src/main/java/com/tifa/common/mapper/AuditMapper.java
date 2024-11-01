package com.tifa.common.mapper;

import com.tifa.common.entity.dto.AuditDto;

/**
 * 审核持久层接口
 *
 * @author lihao
 * &#064;date  2024/11/1--13:18
 * @since 1.0
 */
public interface AuditMapper {
    Integer insert(AuditDto auditDto);
    AuditDto selectById(String id);
    AuditDto selectByAuditId(String auditId);
    Integer update(AuditDto auditDto);
}
