package com.qf.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDetail {
    private Product product;

    private List<ProductSku> skuList;

    private List<ProductComments>  commentList;

    private List<ProductImg> productImgList;

    private List<ProductParams> productParamsList;


}
