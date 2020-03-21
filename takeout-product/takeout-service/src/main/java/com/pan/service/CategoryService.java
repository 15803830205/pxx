package com.pan.service;


import com.pan.dataobject.ProductCategory;

import java.util.List;


public interface CategoryService {

    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);
}
