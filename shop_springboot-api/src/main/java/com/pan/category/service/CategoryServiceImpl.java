package com.pan.category.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.pan.category.dao.CategoryDao;
import com.pan.category.po.Category;
import com.pan.commons.ServerResponse;
import com.pan.commons.SystemConstant;
import com.pan.utils.RedisUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Random;

@Service
public class CategoryServiceImpl implements ICategoryService {
    @Resource
    private CategoryDao categoryDao;

    @Override
    public ServerResponse findList() {
        String categoryJsonStr = RedisUtil.get(SystemConstant.CATELIST_KEY);
        //缓存中有值
        if(StringUtils.isNotBlank(categoryJsonStr)){
            List<Category> categoryList = JSONObject.parseArray(categoryJsonStr, Category.class);
            //随机失效时间
            Random random = new Random();
            int expire = SystemConstant.CATELIST_KEY_EXPIRE + random.nextInt(100);
            //续命
            RedisUtil.expire(SystemConstant.CATELIST_KEY,expire);
            return ServerResponse.success(categoryList);
        }

        //缓存没有
        List<Category> categoryList = categoryDao.selectList(null);
         categoryJsonStr = JSONObject.toJSONString(categoryList);
        if(categoryJsonStr != null){
            RedisUtil.setEx(SystemConstant.CATELIST_KEY,categoryJsonStr,SystemConstant.CATELIST_KEY_EXPIRE);
        }else {
            //设置空值
            RedisUtil.setEx(SystemConstant.CATELIST_KEY,null,SystemConstant.EMPTY_KEY);
        }
        return ServerResponse.success(categoryList);
    }
}
