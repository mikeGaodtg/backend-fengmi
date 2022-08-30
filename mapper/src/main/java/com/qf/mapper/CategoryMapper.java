package com.qf.mapper;

import com.qf.pojo.Category;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 商品分类 Mapper 接口
 * </p>
 *
 * @author 威哥
 * @since 2022-08-17
 */
@Repository
public interface CategoryMapper extends BaseMapper<Category> {
    List<Category> selectCategory();

    @Select("select * from category where category_level =1")
    List<Category> selectCategoryImg();


}
