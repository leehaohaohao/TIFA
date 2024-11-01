package com.tifa.business.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tifa.business.service.DiscoverService;
import com.tifa.common.base.PageVo;
import com.tifa.common.entity.dto.ObjSellDto;
import com.tifa.common.entity.dto.UserInfoDto;
import com.tifa.common.entity.query.ObjSellQuery;
import com.tifa.common.entity.vo.ObjSellCoverVo;
import com.tifa.common.mapper.ObjSellMapper;
import com.tifa.common.mapper.UserInfoMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * 发现业务实现类
 *
 * @author lihao
 * &#064;date  2024/11/1--19:18
 * @since 1.0
 */
@Service
@AllArgsConstructor
public class DiscoverServiceImpl implements DiscoverService {
    private final ObjSellMapper<ObjSellDto, ObjSellQuery> objSellMapper;
    private final UserInfoMapper userInfoMapper;
    @Override
    public Map<String, Object> listSelect(PageVo pageVo) {
        Map<String, Object> result = new HashMap<>();
        //设置查询条件
        ObjSellQuery query = new ObjSellQuery();
        query.setPageVo(pageVo);
        query.setSellStatus(ObjSellDto.SellStatus.PENDING);
        //开启分页
        PageHelper.startPage(pageVo.getPageNum(), pageVo.getPageSize());
        List<ObjSellDto> objSellDtos = objSellMapper.selectList(query);
        //批量查询用户信息
        Set<String> ids = objSellDtos.stream().map(ObjSellDto::getUserId).collect(Collectors.toSet());
        List<UserInfoDto> userInfos = userInfoMapper.selectUsersByIds(ids);
        PageInfo<ObjSellDto> pageInfo = new PageInfo<>(objSellDtos);
        //设置分页信息
        PageVo page = new PageVo();
        page.setPages(pageInfo.getPages());
        page.setTotal(pageInfo.getTotal());
        page.setPageNum(pageInfo.getPageNum());
        page.setHasNext(pageInfo.isHasNextPage());
        page.setHasPrevious(pageInfo.isHasPreviousPage());
        result.put("pageInfo", page);
        //封装封面信息
        List<ObjSellCoverVo> coverList = objSellDtos.stream().map(objSellDto -> {
            ObjSellCoverVo coverVo = new ObjSellCoverVo();
            UserInfoDto userInfoDto = userInfos.stream()
                    .filter(user -> user.getId().equals(objSellDto.getUserId()))
                    .findFirst().orElse(null);
            coverVo.setCover(objSellDto.getCover());
            coverVo.setUserId(objSellDto.getUserId());
            coverVo.setFavorites(objSellDto.getFavorites());
            coverVo.setViews(objSellDto.getViews());
            coverVo.setUsername(userInfoDto.getUsername());
            coverVo.setAvatar(userInfoDto.getAvatar());
            coverVo.setShelfDate(new Date(objSellDto.getShelfDate()));
            return coverVo;
        }).collect(Collectors.toList());
        result.put("coverList", coverList);
        return result;
    }
}
