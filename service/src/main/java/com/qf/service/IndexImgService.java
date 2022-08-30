package com.qf.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qf.pojo.IndexImg;
import com.qf.pojo.ResultData;

/**
 * <p>
 * 轮播图  服务类
 * </p>
 *
 * @author 威哥
 * @since 2022-08-18
 */
public interface IndexImgService  {
    ResultData selectAll();
}
