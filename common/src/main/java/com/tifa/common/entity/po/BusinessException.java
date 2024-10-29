package com.tifa.common.entity.po;

/**
 * 业务异常
 *
 * @author lihao
 * &#064;date  2024/10/28--21:10
 * @since 1.0
 */
public class BusinessException extends Exception{
    public BusinessException(){

    }
    public BusinessException(String message){
        super(message);
    }
}
