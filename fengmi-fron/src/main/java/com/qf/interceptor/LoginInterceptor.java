package com.qf.interceptor;

import com.qf.pojo.ResultData;
import com.qf.pojo.Users;
import com.qf.test.JsonUtils;
import com.qf.test.JwtUtils;
import com.qf.test.RsaUtils;
import org.apache.ibatis.javassist.ClassPath;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.PublicKey;
import java.util.Enumeration;

@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String method = request.getMethod();
        if(method.equals("OPTIONS")){
            return true;
        }

        String token = request.getHeader("token");
        try{
           PublicKey publicKey = RsaUtils.getPublicKey(new ClassPathResource("rsa.pub").getInputStream());
           Users users = (Users) JwtUtils.getInfoFromToken(token, publicKey, Users.class);

            return true;
       }catch (Exception e){
           e.printStackTrace();
       }
       ResultData resultData =new ResultData(403,"token is illegal");
       response.getWriter().write(JsonUtils.toString(resultData));
        return false;
    }
}
