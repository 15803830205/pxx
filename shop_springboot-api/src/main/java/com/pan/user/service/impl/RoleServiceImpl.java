package com.pan.user.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.pan.commons.SystemConstant;
import com.pan.user.common.Results;
import com.pan.user.dao.RoleDao;
import com.pan.user.po.SysRole;
import com.pan.user.service.IRoleService;
import com.pan.utils.RedisUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class RoleServiceImpl implements IRoleService {

    @Autowired
    @SuppressWarnings("all")
    private RoleDao roleDao;

    @Override
    public Results getAllRole() {
        String roleJsonStr = RedisUtil.get(SystemConstant.ROLELIST_KEY);
        if(StringUtils.isNotBlank(roleJsonStr)){
            List<SysRole> roleList = JSONObject.parseArray(roleJsonStr, SysRole.class);
            //续命
            Random random = new Random();
            int expire = SystemConstant.ROLELIST_KEY_EXPIRE + random.nextInt(100);
            RedisUtil.expire(SystemConstant.ROLELIST_KEY,expire);
            return Results.success(roleList);
        }
        List<SysRole> roleList=roleDao.selectList(null);
        if(CollectionUtils.isNotEmpty(roleList)){
             roleJsonStr = JSONObject.toJSONString(roleList);
            RedisUtil.setEx(SystemConstant.ROLELIST_KEY,roleJsonStr,SystemConstant.ROLELIST_KEY_EXPIRE);
        }else {
            RedisUtil.setEx(SystemConstant.ROLELIST_KEY,null,SystemConstant.EMPTY_KEY);
        }
        return Results.success(roleList);
    }
}
