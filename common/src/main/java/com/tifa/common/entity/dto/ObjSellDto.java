package com.tifa.common.entity.dto;

import com.tifa.common.base.BaseEnum;
import lombok.Data;

import java.util.List;

/**
 * 模型出售信息
 *
 * @author lihao
 * &#064;date  2024/11/1--12:29
 * @since 1.0
 */
@Data
public class ObjSellDto {
    // 商单id
    private String id;
    // 模型名称
    private String model;
    // 封面
    private String cover;
    // 用户id
    private String userId;
    // 浏览量
    private Integer views;
    // 收藏量
    private Integer favorites;
    // 标签
    private String tags;
    // 出售规则
    private SellRules sellRules;
    // 出售状态
    private SellStatus sellStatus;
    // 价格
    private Integer prices;
    // 上架时间
    private Long shelfDate;
    // 描述
    private String description;
    // 更新时间
    private Long updateDate;
    public enum SellStatus implements BaseEnum {
        PENDING(0,"待审核"),
        REJECT(1,"审核不通过"),
        SELLING(2,"在售"),
        SOLD_OUT(3,"已售"),
        SHELF_DOWN(4,"已下架"),
        ;
        private final Integer code;
        private final String msg;
        SellStatus(Integer code,String msg){
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
    public enum SellRules implements BaseEnum{
        EXCLUSIVE(0,"独家"),
        MULTIPLE(1,"多家");
        private final Integer code;
        private final String msg;
        SellRules(Integer code,String msg){
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
