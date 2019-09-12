package com.example.exer.ThreadForkAndJoin;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 生成产品随机列表
 */
public class ProductListGenerator {
    public List<Product> generate(int size) {
        List<Product> res = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            Product product = new Product();
            product.setName("Product " + i);
            product.setPrice(BigDecimal.TEN);
            res.add(product);
        }
        return res;
    }
}
