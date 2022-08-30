package com.qf.service;

import com.qf.pojo.ResultData;
import com.qf.pojo.ShoppingCart;

public interface ShoppingCartService {
    ResultData addShoppingCart (ShoppingCart shoppingCart);

    ResultData selectShoppingCart(int userId);

    ResultData deleteOneCart(int cartId);

    ResultData deleteInBatch(ShoppingCart [] shoppingCarts);

    ResultData getCartCount(int userId);
}
