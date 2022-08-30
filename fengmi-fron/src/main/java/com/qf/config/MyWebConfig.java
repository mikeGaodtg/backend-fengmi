package com.qf.config;

import com.qf.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * 作者：威哥
 * 描述：永无Bug
 */
@Configuration  //表示当前类是一个配置类
public class MyWebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")   //请求的资源  /**表示所有资源 、
                .allowedOrigins("*")    //允许指定的源能访问
                .allowedHeaders("*")    //允许携带的请求头
                .allowedMethods("*");    //允许的请求方式
        //注意:当设置允许携带Cookie不允许将指定源设置为所有
    }
}

@Configuration
class MyWebConfig2 implements WebMvcConfigurer {
    @Autowired
    LoginInterceptor loginInterceptor;
    //添加虚拟路径，相同于通过一个路径来访问本地磁盘上的内容
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/img/**")
//                .addResourceLocations("file:D:\\upload\\");
                .addResourceLocations("file:/home/admin/upload/");

    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/shoppingCarts/**")
                .addPathPatterns("/orders/**");

        //order
    }
}