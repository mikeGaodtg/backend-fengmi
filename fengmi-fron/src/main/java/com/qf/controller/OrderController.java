package com.qf.controller;


import com.github.wxpay.sdk.WXPay;
import com.github.wxpay.sdk.WXPayConfig;
import com.qf.config.myWXPayConfig;
import com.qf.pojo.Orders;
import com.qf.pojo.ResultData;
import com.qf.service.OrderService;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("order")
public class OrderController {
    @Autowired
    OrderService orderService;

    @PostMapping
    ResultData addOrders(@RequestBody Orders orders){
        ResultData resultData = orderService.addOrder(orders);
        return resultData;
    }

    @GetMapping("{userId}")
    ResultData getOrderById(@PathVariable("userId") Integer id){
        return orderService.getOrder(id);
    }

    @PostMapping("pay/{orderId}")
    ResultData payOrder(@PathVariable("orderId") String orderId){

        try {
            ResultData resultData = orderService.getOrderByPrimaryKey(orderId);
            Map<String,String> map = new HashMap<>();
            map.put("body","锋迷商城");   //支付信息
            map.put("out_trade_no",orderId+""); //订单编号   唯一
            map.put("fee_type","CNY"); //支付币种
            map.put("total_fee","1");  //支付金额  单位为分
            map.put("trade_type","NATIVE"); //支付类型  扫码支付
            map.put("notify_url","http://47.74.87.88:8080/payNotify");// 支付回调地址  内网穿透

            WXPay wxPay = new WXPay(new myWXPayConfig());
            //微信支付平台返回的结果   统一下单接口的API方法
            Map<String, String> resultMap = wxPay.unifiedOrder(map);
            //支付连接
            String code_url = resultMap.get("code_url");

            Map<String,Object> respMap = new HashMap<>();
            respMap.put("orders",resultData.getData());
            respMap.put("code_url",code_url);
            return new ResultData(0,"下单成功",respMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResultData(100,"下单失败");
    }

    @DeleteMapping("delete/{orderId}")
    ResultData deleteOrder(@PathVariable("orderId") String orderId){
        ResultData resultData = orderService.deleteOrderById(orderId);
        return resultData;
    }

}



