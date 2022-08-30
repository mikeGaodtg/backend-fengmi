package com.qf.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qf.pojo.Product;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ProductMapper extends BaseMapper<Product> {
    List<Product> selectProJoinPicByCategory(@Param("categoryId") Integer categoryId);

    Product selectById(@Param("productId") int id);
}
