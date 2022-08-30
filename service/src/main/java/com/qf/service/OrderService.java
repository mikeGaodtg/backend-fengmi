package com.qf.service;

import com.qf.pojo.Orders;
import com.qf.pojo.ResultData;

import javax.xml.transform.Result;

public interface OrderService {
    ResultData addOrder(Orders orders);

    ResultData getOrder(int id);

    ResultData getOrderByPrimaryKey(String id);

    ResultData updateOrderStatus(String orderid);

    ResultData deleteOrderById(String orderId);
}
