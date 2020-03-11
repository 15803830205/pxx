package com.pan.shop.api.category.controller;

import com.pan.shop.api.category.service.ICategoryService;
import com.pan.shop.api.commons.ServerResponse;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategoryController {

    private final ICategoryService categoryService;

    public CategoryController(ICategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/findList")
    public Object findAllList(String callback){
        ServerResponse list = categoryService.findAllList();
        // 将数据传递给前端
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(list);
        // 调用客户端的回调函数
        mappingJacksonValue.setJsonpFunction(callback);
        return mappingJacksonValue;
    }


}
