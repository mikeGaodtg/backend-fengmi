package com.qf.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qf.mapper.ShoppingCartMapper;
import com.qf.pojo.ResultData;
import com.qf.pojo.ShoppingCart;
import com.qf.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import java.util.stream.Collectors;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    @Autowired
    ShoppingCartMapper shoppingCartMapper;

    @Override
    public ResultData addShoppingCart(ShoppingCart shoppingCart) {
        shoppingCart.setCartTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        int count = shoppingCartMapper.insert(shoppingCart);
        if(count>0){
            return new ResultData(0, "successfully");
        }
        return new ResultData(100, "successfully");
    }

    @Override
    public ResultData selectShoppingCart(int userId) {

        List<ShoppingCart> shoppingCartList = shoppingCartMapper.selectShoppingCarts(userId);
        if(shoppingCartList!=null&&shoppingCartList.size()>0){
            return new ResultData(0,"success",shoppingCartList);
        }
        if(shoppingCartList!=null && shoppingCartList.size()==0){
            return  new ResultData(0,"cart is empty");
        }
        return new ResultData(100,"fail");
    }

    @Override
    public ResultData deleteOneCart(int cartId) {
        int count = shoppingCartMapper.deleteById(cartId);
        if(count>0){
            return new ResultData(0,"one cart is deleted");
        }
        return new ResultData(100,"delete fail");
    }
    @Transactional
    @Override
    public ResultData deleteInBatch(ShoppingCart[] shoppingCarts) {
        List<Integer> list = Arrays.stream(shoppingCarts).map(li -> li.getCartId()).collect(Collectors.toList());
        int count = shoppingCartMapper.deleteBatchIds(list);
        if(count>0){
            return new ResultData(0,count+"item has been deleted");
        }
        return new ResultData(100,"fail");
    }

    @Override
    public ResultData getCartCount(int userId) {
        QueryWrapper<ShoppingCart> queryWrapper =new QueryWrapper<>();
        queryWrapper.eq("user_id",userId);
        Integer count = shoppingCartMapper.selectCount(queryWrapper);
        if(count!=null){
            return new ResultData(0,"success",count);
        }
        return new ResultData(0,"fail");
    }


}
