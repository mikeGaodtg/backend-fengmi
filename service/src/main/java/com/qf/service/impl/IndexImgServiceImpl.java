package com.qf.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qf.mapper.IndexImgMapper;
import com.qf.pojo.IndexImg;
import com.qf.pojo.ResultData;
import com.qf.service.IndexImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * <p>
 * 轮播图  服务实现类
 * </p>
 *
 * @author 威哥
 * @since 2022-08-18
 */
@Service
public class IndexImgServiceImpl  implements IndexImgService {
    @Autowired
    IndexImgMapper indexImgMapper;
    @Override

    public ResultData selectAll() {
        QueryWrapper<IndexImg> queryWrapper =new QueryWrapper<>();
        queryWrapper.eq("status","1");
        queryWrapper.orderByAsc("seq");
        List<IndexImg> indexImgList = indexImgMapper.selectList(queryWrapper);
        if(indexImgList!=null&&indexImgList.size()>0){
            return new ResultData(0,"success",indexImgList);
        }
        return new ResultData(100,"fail");
    }
}
