package com.qf.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qf.mapper.UserAddrMapper;
import com.qf.pojo.ResultData;
import com.qf.pojo.UserAddr;
import com.qf.service.UserAddrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class UserAddrServiceImpl  implements UserAddrService {
    @Autowired
    private UserAddrMapper userAddrMapper;
    @Override
     public ResultData getUserAllAdrress(int id) {
        QueryWrapper<UserAddr> queryWrapper = new QueryWrapper();
        queryWrapper.eq("user_id",id);
        List<UserAddr> userAddrList = userAddrMapper.selectList(queryWrapper);
        if(userAddrList!=null&&userAddrList.size()>0){
            return new ResultData(0,"success",userAddrList);
        }
        return new ResultData(100,"fail");
    }

    @Transactional
    @Override
    public ResultData changeAddress(int oldAddress, int newAddress) {
        int count1 = userAddrMapper.changeAddressById(oldAddress, 1);
        int count2 = userAddrMapper.changeAddressById(newAddress, 0);
        if(count1>0&&count2>0){
            return new ResultData(0,"sucess");
        }

        return new ResultData(100,"fail");
    }

    @Override
    public ResultData updateAddress(UserAddr userAddr) {
        QueryWrapper queryWrapper= new QueryWrapper();
        queryWrapper.eq("addr_id",userAddr.getAddrId());
        int count = userAddrMapper.update(userAddr,queryWrapper);
        if(count>0){
            return new ResultData(0,"success");
        }
        return new ResultData(100,"fail");
    }

    @Override
    public ResultData addAddress(UserAddr userAddr) {

        QueryWrapper<UserAddr> queryWrapper =new QueryWrapper<>();
        queryWrapper.eq("user_id",userAddr.getUserId());
        List<UserAddr> ifUserAddressAvail = userAddrMapper.selectList(queryWrapper);
        if(ifUserAddressAvail==null || ifUserAddressAvail.size()==0){
            userAddr.setStatus(1);
        }
        userAddr.setCreateTime(new Date());
        int count = userAddrMapper.insert(userAddr);
        if(count>0){
            return new ResultData(0,"success");
        }
        return new ResultData(100,"fail");
    }
}
