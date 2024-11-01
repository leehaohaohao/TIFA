package com.tifa.common.entity.dto;


import com.tifa.common.base.BaseEnum;
import lombok.Data;


/**
 * 审核 DTO
 *
 * @author lihao
 * &#064;date  2024/11/1--12:32
 * @since 1.0
 */
@Data
public class AuditDto{
    // 审核记录id
    private String id;
    // 审核人id
    private String auditId;
    // 审核对象id
    private String objId;
    // 审核状态
    private AuditStatus auditStatus;
    // 审核意见
    private String comments;
    // 审核时间
    private Long auditDate;
    // 审核类型
    private AuditType auditType;
    public enum AuditType implements BaseEnum {
        MODEL(0,"模型");
        private final Integer code;
        private final String msg;
        AuditType(Integer code,String msg){
            this.code=code;
            this.msg=msg;
        }
        @Override
        public Integer getCode() {
            return this.code;
        }
        @Override
        public String getMsg() {
            return this.msg;
        }
    }
    public enum AuditStatus implements BaseEnum {
        PENDING(0,"待审核"),
        PASS(1,"审核通过"),
        REJECT(2,"审核不通过");
        private final Integer code;
        private final String msg;
        AuditStatus(Integer code,String msg){
            this.code=code;
            this.msg=msg;
        }
        @Override
        public Integer getCode() {
            return this.code;
        }
        @Override
        public String getMsg() {
            return this.msg;
        }
    }
}
