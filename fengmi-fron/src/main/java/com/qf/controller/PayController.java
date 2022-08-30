package com.qf.controller;


import com.github.wxpay.sdk.WXPay;
import com.github.wxpay.sdk.WXPayUtil;
import com.qf.pojo.ResultData;
import com.qf.service.OrderService;
import com.qf.webSocket.WebSocketServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController

public class PayController {
    @Autowired
    private OrderService orderService;
    @RequestMapping("payNotify")
    public String notifyAfterPay(HttpServletRequest request) throws IOException {

        ServletInputStream inputStream = request.getInputStream();
        byte[] buf =new byte[1024];
        int len;
        StringBuffer sb = new StringBuffer();
        while ((len=inputStream.read(buf))!=-1){
            sb.append(new String(buf,0,len));
        }
        try {
            Map<String, String> resultMap = WXPayUtil.xmlToMap(sb.toString());
            if(resultMap!=null&&resultMap.get("result_code").equalsIgnoreCase("SUCCESS")){
                String orderId = resultMap.get("out_trade_no");
                ResultData resultData = orderService.updateOrderStatus(orderId);
                WebSocketServer.sendMsg(orderId,"payment is done");

                if(resultData.getCode()==0){
                    HashMap<String,String> responseMap = new HashMap<String, String>();
                    responseMap.put("return_code","success");
                    responseMap.put("return_msg","OK");
                    responseMap.put("appid",resultMap.get("appid"));
                    responseMap.put("result_code","success");
                    return WXPayUtil.mapToXml(responseMap);
                }
            }else {
                String orderId = resultMap.get("out_trade_no");
                WebSocketServer.sendMsg(orderId,"payment is done");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }


}
