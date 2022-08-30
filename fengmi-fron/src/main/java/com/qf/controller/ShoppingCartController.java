package com.qf.controller;


import com.qf.pojo.ResultData;
import com.qf.pojo.ShoppingCart;
import com.qf.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/shoppingCart")
public class ShoppingCartController {
    @Autowired
    private ShoppingCartService shoppingCartService;
    @PostMapping
    public ResultData addShopping(@RequestBody ShoppingCart shoppingCart){
        ResultData resultData = shoppingCartService.addShoppingCart(shoppingCart);
        return resultData;
    }

    @GetMapping("{userId}")
    public ResultData getShoppingCart(@PathVariable Integer userId){
        ResultData resultData = shoppingCartService.selectShoppingCart(userId);
        return resultData;
    }

    @DeleteMapping("{cartId}")
    public ResultData deleteOne(@PathVariable Integer cartId){
        ResultData resultData = shoppingCartService.deleteOneCart(cartId);
        return resultData;
    }

    @DeleteMapping("deleteBatch")
    public ResultData deleteBatch(@RequestBody ShoppingCart[] shoppingCarts){
        ResultData resultData = shoppingCartService.deleteInBatch(shoppingCarts);
        return resultData;
    }

    @GetMapping("count/{userId}")
    public ResultData getcount(@PathVariable("userId") Integer userId){
        ResultData resultData = shoppingCartService.getCartCount(userId);
        return resultData;
    }
}
