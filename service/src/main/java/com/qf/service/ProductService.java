package com.qf.service;

import com.qf.pojo.ResultData;


public interface ProductService {
    ResultData selectByPage(int pageNum,int pageSize);

    ResultData selectByCategoryId(int id,int pageNum,int pageSize);

    ResultData selectProJoinPicByCategory(int id);

    ResultData selectProDetails(int id);


}
