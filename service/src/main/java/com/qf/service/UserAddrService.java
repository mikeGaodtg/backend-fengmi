package com.qf.service;

import com.qf.pojo.ResultData;
import com.qf.pojo.UserAddr;

public interface UserAddrService {
    ResultData getUserAllAdrress(int id);

    ResultData changeAddress(int oldAddress, int newAddress);

    ResultData updateAddress(UserAddr userAddr);

    ResultData addAddress(UserAddr userAddr);
}
