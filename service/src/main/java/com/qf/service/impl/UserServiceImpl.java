package com.qf.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qf.mapper.UsersMapper;
import com.qf.pojo.ResultData;
import com.qf.pojo.Users;
import com.qf.service.UserService;
import com.qf.test.JwtUtils;
import com.qf.test.RsaUtils;
import io.jsonwebtoken.Jwt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.util.ResourceUtils;

import java.io.FileNotFoundException;
import java.security.PrivateKey;
import java.util.Date;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UsersMapper usersMapper;
    @Override
    public ResultData login(Users userFromClient) {
        QueryWrapper<Users> queryWrapper = new QueryWrapper();
        queryWrapper.eq("username",userFromClient.getUsername());
        Users userFromDB = usersMapper.selectOne(queryWrapper);
        if(userFromDB==null){
            return  new ResultData(101,"user doesnt exist");
        }
        if(!userFromClient.getUsername().equals(userFromDB.getUsername())){
            return  new ResultData(100,"user doesnt exist");
        }
        if(!DigestUtils.md5DigestAsHex(userFromClient.getPassword().getBytes()).equals(userFromDB.getPassword())){
            return  new ResultData(102,"password not right");
        }
        userFromClient.setPassword("");

        try {
            PrivateKey privateKey =
                    RsaUtils.getPrivateKey(new ClassPathResource("rsa.pri").getInputStream());
            String token =
                    JwtUtils.generateTokenExpireInMinutes(userFromClient, privateKey, 300);
            userFromClient.setToken(token);
            userFromClient.setUserId(userFromDB.getUserId());
            return new ResultData(0,"successfully login",userFromClient);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultData( 120,"token generation failure");
        }

    }

    @Override
    public ResultData addUser(Users users) {
        QueryWrapper<Users> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("username",users.getUsername());
        Users isRepeated = usersMapper.selectOne(queryWrapper);
        if(isRepeated!=null){
            return new ResultData(101,"userExist");
        }
//        DigestUtils.
        users.setPassword(DigestUtils.md5DigestAsHex(users.getPassword().getBytes()));
        users.setUserRegtime(new Date());
        int insert = usersMapper.insert(users);
        if(insert>0){
            return new ResultData(0,"success");
        }
        return new ResultData(100,"fail");
    }
}
