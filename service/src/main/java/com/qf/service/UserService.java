package com.qf.service;

import com.qf.pojo.ResultData;
import com.qf.pojo.Users;

public interface UserService {
    ResultData login (Users users);

    ResultData addUser (Users users);
}
