package com.qf.service.impl;

import com.qf.mapper.CategoryMapper;
import com.qf.pojo.Category;
import com.qf.pojo.ResultData;
import com.qf.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryMapper categoryMapper;
    @Override
    public ResultData selectCategory() {
        List<Category> categoryList = categoryMapper.selectCategory();
        if(categoryList!=null &&categoryList.size()>0){
            return new ResultData(0,"success",categoryList);
        }
        return new ResultData(100,"fail");
    }

    @Override
    public ResultData selectCategoryPic() {
        List<Category> categoryList = categoryMapper.selectCategoryImg();
        if(categoryList.size()>0&&categoryList!=null){
            return new ResultData(0,"success",categoryList);
        }
        return new ResultData(0,"success");
    }
    @Transactional
    @Override
    public ResultData addCategory(Category category) {
        int count = categoryMapper.insert(category);
        if(count>0){
            return new ResultData(0, count+" has been affected");
        }
        return new ResultData(0, "fail to add");
    }
}
