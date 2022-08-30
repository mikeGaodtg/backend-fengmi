package com.qf.webSocket;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

import javax.websocket.OnClose;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
@Component
@ServerEndpoint("/websokect/{orderId}")
public class WebSocketServer {
    private static ConcurrentHashMap<String ,Session> sessionMap =new ConcurrentHashMap();

    @OnOpen
    public void open(@PathParam("orderId") String userId, Session session){
        sessionMap.put(userId,session);
    }

    @OnClose
    public void close(@PathParam("orderId")  String userId,Session session){
        sessionMap.remove(userId);
    }

    public static void sendMsg(String userId,String msg){
        Session session = sessionMap.get(userId);
        try {
            session.getBasicRemote().sendText(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
