package com.qf.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * 作者：威哥
 * 描述：永无Bug
 */
@Configuration  //表示当前类是配置类
public class WebSocketConfig {

    @Bean //将当前方法的方法的值对象添加到Spring容器中
    public ServerEndpointExporter getServerEndpointExporter(){
        return new ServerEndpointExporter();
    }
}
