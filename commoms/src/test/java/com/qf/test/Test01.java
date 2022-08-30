package com.qf.test;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import org.junit.Test;
import org.springframework.util.DigestUtils;
import org.springframework.util.ResourceUtils;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.springframework.util.DigestUtils;

public class Test01 {
//    @Test
//    public void testPublicKeyAndPrivateKey() throws Exception {
//        //使用工具类生成公钥和私钥（确保路径存在）
//        String privateKeyFileName = "D://workspace//key//rsa.pri";
//        String publicKeyFileName = "D://workspace//key//rsa.pub";
//        RsaUtils.generateKey(publicKeyFileName,privateKeyFileName,"qfjava",2048);
//    }
//    @Test
//    public void testValidatePublicKeyAndPrivateKey() throws Exception {
//        //私钥加密，公钥解密
//        //获取私钥文件路径
//        String privateFile = ResourceUtils.getFile("classpath:key/rsa.pri").getPath();
//        //获取私钥对象
//        PrivateKey privateKey = RsaUtils.getPrivateKey(privateFile);
//        //生成Token
//        HashMap map = new HashMap();
//        map.put("username","zhangsan");
//        map.put("role","ROLE_admin,ROLE_user");
//        String token = JwtUtils.generateTokenExpireInSeconds(map, privateKey, 10);
//
//
//        //获取公钥文件路径
//        String publicFile = ResourceUtils.getFile("classpath:key/rsa.pub").getPath();
//        //获取公钥对象
//        PublicKey publicKey = RsaUtils.getPublicKey(publicFile);
//        Map infoFromToken = (Map) JwtUtils.getInfoFromToken(token, publicKey, Map.class);
//        System.out.println(infoFromToken.get("username"));
//        System.out.println(infoFromToken.get("role"));
//    }
//
//    @Test
//    public void test01(){
//        String s = DigestUtils.md5DigestAsHex("123456".getBytes());
//        System.out.println(s);
//    }
}
