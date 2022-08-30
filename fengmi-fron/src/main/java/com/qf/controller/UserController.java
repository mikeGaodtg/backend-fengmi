package com.qf.controller;

import com.qf.pojo.ResultData;
import com.qf.pojo.Users;
import com.qf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("login")
    public ResultData login(@RequestBody Users user){

        ResultData resultData = userService.login(user);
        return resultData;
    }

    @PutMapping("add")
    public ResultData addUser(@RequestBody Users users){
        ResultData resultData = userService.addUser(users);
        return resultData;
    }
}
