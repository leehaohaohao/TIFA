package com.tifa.common.entity.dto;

import cn.hutool.core.util.StrUtil;
import com.tifa.common.enums.CodeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 响应包装类
 *
 * @author lihao
 * &#064;date  2024/9/26--18:57
 * @since 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponsePack<T> {
    // 状态码
    private Integer code;
    // 消息
    private String msg;
    // 数据
    private T data;
    // 是否成功
    private boolean success;
    public static <T> ResponsePack<T> success(T data){
        return new ResponsePack<>(CodeEnum.SUCCESS.getCode(), CodeEnum.SUCCESS.getMsg(),data,true);
    }
    public static <T> ResponsePack<T> success(T data, Integer code){
        return new ResponsePack<>(code, CodeEnum.SUCCESS.getMsg(),data,true);
    }
    public static <T> ResponsePack<T> success(T data, Integer code, String msg){
        if(StrUtil.isBlank(msg)){
            msg=null;
        }
        return new ResponsePack<>(code, msg,data,true);
    }
    public static <T> ResponsePack<T> fail(Integer code, String msg){
        if(StrUtil.isBlank(msg)){
            msg=null;
        }
        return new ResponsePack<>(code, msg,null,false);
    }
    public static <T> ResponsePack<T> fail(T data,Integer code, String msg){
        if(StrUtil.isBlank(msg)){
            msg=null;
        }
        return new ResponsePack<>(code, msg,data,false);
    }
    public static <T> ResponsePack<T> fail(String msg){
        if(StrUtil.isBlank(msg)){
            msg=null;
        }
        return new ResponsePack<>(CodeEnum.FAIL.getCode(), msg,null,false);
    }
}
