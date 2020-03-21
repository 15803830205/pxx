package com.pan.controller;

import com.pan.common.DecreaseStockInput;
import com.pan.service.ProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ProductController {

    @Autowired
    private ProductService productService;

    @Test
    public void decreaseStock(@RequestBody List<DecreaseStockInput> decreaseStockInputList) {
        List<DecreaseStockInput> decreaseStockInput1= new ArrayList<>();
        DecreaseStockInput decreaseStockInput =new DecreaseStockInput();
        decreaseStockInput.setProductId("157875196366160022");
        decreaseStockInput.setProductQuantity(1);
        decreaseStockInput1.add(decreaseStockInput);
        productService.decreaseStock(decreaseStockInput1);
    }
}
