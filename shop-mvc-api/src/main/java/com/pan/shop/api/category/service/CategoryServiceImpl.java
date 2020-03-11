package com.pan.shop.api.category.service;

import com.pan.shop.api.category.dao.CategoryDao;
import com.pan.shop.api.category.po.Category;
import com.pan.shop.api.commons.ServerResponse;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service

public class CategoryServiceImpl implements ICategoryService {
    @Resource
    private CategoryDao categoryDao;

    @SuppressWarnings("all")
    public CategoryServiceImpl(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    @Override
    public ServerResponse findAllList() {
        List<Category> categoryList = categoryDao.selectList(null);
        return ServerResponse.success(categoryList);
    }
}
