package com.pan.category.controller;

import com.pan.category.service.ICategoryService;
import com.pan.commons.ServerResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping
@CrossOrigin(maxAge = 1800,origins = "http://localhost:7777")
@Api(tags = "商品分类")
public class CategoryController {

    @Autowired
    private ICategoryService categoryService;

    @GetMapping("/findList")
    @ApiOperation(value = "查询分类")
    public ServerResponse findList(){
        return categoryService.findList();
    }
}
