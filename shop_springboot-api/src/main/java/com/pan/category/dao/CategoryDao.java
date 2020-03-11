package com.pan.category.dao;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pan.category.po.Category;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CategoryDao extends BaseMapper<Category> {
}
