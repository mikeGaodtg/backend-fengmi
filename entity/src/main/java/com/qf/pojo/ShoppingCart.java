package com.qf.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 购物车 
 * </p>
 *
 * @author 威哥
 * @since 2022-08-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ShoppingCart implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "cart_id", type = IdType.AUTO)
    private Integer cartId;

    /**
     * 商品ID
     */
    private String productId;

    /**
     * skuID
     */
    private String skuId;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 购物车商品数量
     */
    private String cartNum;

    /**
     * 添加购物车时间
     */
    private String cartTime;

    /**
     * 添加购物车时商品价格
     */
    private BigDecimal productPrice;

    /**
     * 选择的套餐的属性
     */
    private String skuProps;

    public ShoppingCart(Integer cartId, String productId, String skuId, String userId, String cartNum, String cartTime, BigDecimal productPrice, String skuProps) {
        this.cartId = cartId;
        this.productId = productId;
        this.skuId = skuId;
        this.userId = userId;
        this.cartNum = cartNum;
        this.cartTime = cartTime;
        this.productPrice = productPrice;
        this.skuProps = skuProps;
    }

    //one to one relationship
    @TableField(exist = false)
    private Users users;
    //one to one relationship
    @TableField(exist = false)
    private Product product;

    @TableField(exist = false)
    private ProductSku productSku;
}
