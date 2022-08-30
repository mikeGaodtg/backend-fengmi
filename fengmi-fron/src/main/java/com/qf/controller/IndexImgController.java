package com.qf.controller;


import com.qf.pojo.ResultData;
import com.qf.service.IndexImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 轮播图  前端控制器
 * </p>
 *
 * @author 威哥
 * @since 2022-08-18
 */
@RestController
@RequestMapping("/indexImg")
public class IndexImgController {
    @Autowired
    IndexImgService indexImgService;
    @GetMapping
    ResultData getImg(){
        ResultData resultData = indexImgService.selectAll();
        return resultData;
    }
}

