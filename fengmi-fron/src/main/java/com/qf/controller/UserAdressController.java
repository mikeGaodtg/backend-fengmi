package com.qf.controller;

import com.qf.pojo.ResultData;
import com.qf.pojo.UserAddr;
import com.qf.service.UserAddrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("address")
public class UserAdressController {
    @Autowired
    private UserAddrService userAddrService;
    @GetMapping("all/{userId}")
    ResultData getAllAddress(@PathVariable("userId") Integer userId){
        ResultData userAllAdrress = userAddrService.getUserAllAdrress(userId);
        return userAllAdrress;
    }

    @PutMapping("change/{oldAddr}/{newAddr}")
    ResultData changeAddress(@PathVariable("oldAddr") Integer oldAddr,@PathVariable("newAddr") Integer newAddr){
        ResultData resultData = userAddrService.changeAddress(oldAddr, newAddr);
        return resultData;
    }

    @PutMapping("update")
    ResultData updateAddress(@RequestBody UserAddr userAddr){
        ResultData resultData = userAddrService.updateAddress(userAddr);
        return resultData;
    }

    @PutMapping("add")
    ResultData addAddress(@RequestBody UserAddr userAddr){
        ResultData resultData = userAddrService.addAddress(userAddr);
        return resultData;
    }
}
