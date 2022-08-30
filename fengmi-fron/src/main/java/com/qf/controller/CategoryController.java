package com.qf.controller;

import com.qf.pojo.Category;
import com.qf.pojo.ResultData;
import com.qf.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;
    @GetMapping
    ResultData getCategory(){
        ResultData resultData = categoryService.selectCategory();
        return resultData;
    }
    @GetMapping("pic")
    ResultData getLevel3Pic(){
        ResultData resultData = categoryService.selectCategoryPic();
        return resultData;
    }

    @PostMapping()
    ResultData addCategory(Category category){
        ResultData resultData = categoryService.addCategory(category);
        return resultData;
    }
}
