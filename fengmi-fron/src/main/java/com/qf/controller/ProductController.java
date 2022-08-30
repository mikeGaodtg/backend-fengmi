package com.qf.controller;


import com.qf.pojo.ResultData;
import com.qf.service.ProductService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("product")
public class ProductController {
    @Autowired
    ProductService productService;

    @GetMapping("{categoryId}")
    ResultData getProductByCategory(@PathVariable("categoryId")  Integer id, @RequestParam(defaultValue ="1" ) Integer pageNum, @RequestParam(defaultValue = "5") Integer paseSize){
        ResultData resultData = productService.selectByCategoryId(id, pageNum, paseSize);
        return resultData;

    }
    @GetMapping("withpic/{categoryId}")
    ResultData selectProJoinPicByCategory(@PathVariable("categoryId") Integer id){
        ResultData resultData = productService.selectProJoinPicByCategory(id);
        return resultData;
    }

    @GetMapping("details/{productId}")
    ResultData details(@PathVariable("productId") Integer id){
        ResultData resultData = productService.selectProDetails(id);
        return resultData;
    }
//    @GetMapping("byProductId/{productId}")
//    ResultData selectByProId(@PathVariable("productId") Integer id){
//        ResultData resultData = productService.selecteProductById(id);
//        return resultData;
//    }
}
