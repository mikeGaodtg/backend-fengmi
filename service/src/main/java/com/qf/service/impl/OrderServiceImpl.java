package com.qf.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.qf.mapper.OrdersMapper;
import com.qf.mapper.ProductSkuMapper;
import com.qf.mapper.ShoppingCartMapper;
import com.qf.pojo.Orders;
import com.qf.pojo.ProductSku;
import com.qf.pojo.ResultData;
import com.qf.pojo.ShoppingCart;
import com.qf.service.OrderService;
import com.qf.test.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;


@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    ShoppingCartMapper shoppingCartMapper;

    @Autowired
    ProductSkuMapper productSkuMapper;

    @Autowired
    OrdersMapper ordersMapper;

    @Transactional
    @Override
    public ResultData addOrder( Orders orders) {
        Integer[] cartIds = orders.getShoppingIds();
        for (Integer cartId : cartIds) {
            ShoppingCart shoppingCart = shoppingCartMapper.selectById(cartId);
            ProductSku productSku= productSkuMapper.selectById(shoppingCart.getSkuId());
            if(productSku.getStock()<Integer.parseInt(shoppingCart.getCartNum())){
                return new ResultData(100,"not enough stock");
            }
        }
//        String orderId = UUID.randomUUID().toString().replace("-", "");
        String orderId = DateUtils.createOrderId();
        orders.setOrderId(orderId);
        orders.setCreateTime(new Date());
        orders.setActualAmount(orders.getTotalAmount().intValue());
        int count = ordersMapper.insert(orders);

        //change stock

        for (Integer cartid : cartIds) {
            ShoppingCart shoppingCart = shoppingCartMapper.selectById(cartid);
            ProductSku productSku = productSkuMapper.selectById(shoppingCart.getSkuId());
            productSku.setStock(productSku.getStock()-Integer.parseInt(shoppingCart.getCartNum()));
            int i = productSkuMapper.updateById(productSku);
        }

        int counts = shoppingCartMapper.deleteBatchIds(Arrays.asList(cartIds));
        return new ResultData(0,"order is added");
    }

    @Override
    public ResultData getOrder(int id) {
        QueryWrapper<Orders> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", id);
        List<Orders> ordersList = ordersMapper.selectList(queryWrapper);
        if(ordersList!=null&&ordersList.size()>0) {
            return new ResultData(0,"success",ordersList);
        }
        return new ResultData(100,"fail");
    }

    @Override
    public ResultData getOrderByPrimaryKey(String id) {
        Orders orders = ordersMapper.selectById(id);
        return new ResultData(0,"success",orders);
    }

    @Override
    public ResultData updateOrderStatus(String orderid) {
        Orders orders = ordersMapper.selectById(orderid);
        orders.setStatus("2");
        orders.setPayTime(new Date());
        int i = ordersMapper.updateById(orders);
        if(i>0){
            return new ResultData(0,"success");
        }
        return new ResultData(0,"fail");
    }

    @Override
    public ResultData deleteOrderById(String orderId) {
        int count = ordersMapper.deleteById(orderId);
        if(count>0){
            return new ResultData(0,"success");
        }
        return new ResultData(100,"fail");
    }


}
