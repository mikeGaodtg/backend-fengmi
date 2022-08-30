package com.qf.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qf.mapper.*;
import com.qf.pojo.*;
import com.qf.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;


@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private ProductCommentsMapper productCommentsMapper;
    @Autowired
    private ProductImgMapper productImgMapper;
    @Autowired
    private ProductParamsMapper productParamsMapper;
    @Autowired
    private ProductSkuMapper productSkuMapper;

    @Override
    public ResultData selectProDetails(int id) {

        Product product = productMapper.selectById(id);

        QueryWrapper<ProductSku> queryWrapperSku =new QueryWrapper();
        queryWrapperSku.eq("product_id",id);
        List<ProductSku> skuList = productSkuMapper.selectList(queryWrapperSku);

        QueryWrapper<ProductComments> queryWrapperCom =new QueryWrapper();
        queryWrapperSku.eq("product_id",id);
        List<ProductComments>  commentList = productCommentsMapper.selectList(queryWrapperCom);

        QueryWrapper<ProductImg> productImgQueryWrapper =new QueryWrapper();
        queryWrapperSku.eq("item_id",id);
        List<ProductImg> productImgList = productImgMapper.selectList(productImgQueryWrapper);

        QueryWrapper<ProductParams> queryWrapperParams =new QueryWrapper();
        queryWrapperSku.eq("product_id",id);
        List<ProductParams> productParamsList = productParamsMapper.selectList(queryWrapperParams);

        ProductDetail productDetail = new ProductDetail(product,skuList,commentList,productImgList,productParamsList);


        return new ResultData(0,"success",productDetail);
    }
    @Override
    public ResultData selectByPage(int pageNum, int pageSize) {
        Page page =new Page(pageNum,pageSize);
        productMapper.selectPage(page,null);
        if(page.getRecords()!=null&page.getRecords().size()>0){
            return new ResultData(0,"success",page);
        }
        return new ResultData(100,"no data");
    }

    @Override
    public ResultData selectByCategoryId(int id, int pageNum, int pageSize) {
        Page page = new Page(pageNum,pageSize);
        QueryWrapper<Product> queryWrapper =new QueryWrapper<>();
        queryWrapper.eq("category_id", id);
        queryWrapper.orderByAsc("sold_num");
        productMapper.selectPage(page,queryWrapper);
        List<Product> productList=page.getRecords();
        if(productList!=null&&productList.size()>0){
            return new ResultData(0,"success",page);
        }
        return new ResultData(100,"fail");
    }

    @Override
    public ResultData selectProJoinPicByCategory(int id) {
        List<Product> productList = productMapper.selectProJoinPicByCategory(id);
        if(productList!=null&&productList.size()>0){
            return new ResultData(0,"sucess",productList);
        }
        return new ResultData(0,"fail");
    }


}
