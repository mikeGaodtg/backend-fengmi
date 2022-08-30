package com.qf.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qf.pojo.ShoppingCart;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 购物车  Mapper 接口
 * </p>
 *
 * @author 威哥
 * @since 2022-08-22
 */
public interface ShoppingCartMapper extends BaseMapper<ShoppingCart> {
    List<ShoppingCart> selectShoppingCarts(@Param("userId") int userID);
}
