package com.tifa.business.controller;

import com.tifa.common.base.ResponsePack;
import com.tifa.common.entity.vo.HomePageVo;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * 网页内容管理
 *
 * @author lihao
 * &#064;date  2024/11/1--16:11
 * @since 1.0
 */
@RestController
@RequestMapping("/content")
@CrossOrigin
public class ContentManagerController {
    /**
     * 获取首页资源信息
     * @return
     */
    @GetMapping("/home")
    public ResponsePack homePage(){
        String start = "http://121.40.154.188:8000/tifa";
        String img1 = start+"/test1.png";
        String img2 = start+"/test2.png";
        String img3 = start+"/test3.png";
        String img4 = start+"/test4.png";
        String img5 = start+"/test5.png";
        String img6 = start+"/test6.png";
        String img7 = start+"/test7.png";
        String img8 = start+"/test8.png";
        List<HomePageVo> pageVos = new ArrayList<>();
        HomePageVo homePageVo = new HomePageVo();
        homePageVo.setTitle("教学园地");
        homePageVo.setSubTitle("以一个简单的3D模型开始你的创意");
        homePageVo.setId("0");
        homePageVo.setImages(new String[]{img1,img2,img3,img4});
        homePageVo.setPath("/home/ideas");
        pageVos.add(homePageVo);
        HomePageVo homePageVo1 = new HomePageVo();
        homePageVo1.setTitle("发现灵感");
        homePageVo1.setSubTitle("从我们充满创意的社区寻找和发现你的灵感");
        homePageVo1.setId("1");
        homePageVo1.setImages(new String[]{img5,img6,img7,img8});
        homePageVo1.setPath("/home/ideas");
        pageVos.add(homePageVo1);
        return ResponsePack.success(pageVos);
    }
}
