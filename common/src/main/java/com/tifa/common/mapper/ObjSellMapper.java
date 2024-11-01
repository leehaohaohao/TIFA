package com.tifa.common.mapper;

import com.tifa.common.entity.dto.ObjSellDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * obj_sell表操作
 *
 * @author lihao
 * &#064;date  2024/11/1--16:50
 * @since 1.0
 */
public interface ObjSellMapper<T,Q> {
    List<ObjSellDto> selectList(Q q);
    ObjSellDto selectById(String id);
    Integer insert(T t);
    Integer updateById(@Param("bean")T t,@Param("id") String id);
}
