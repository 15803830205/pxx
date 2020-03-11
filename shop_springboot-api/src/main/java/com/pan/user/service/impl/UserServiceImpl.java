package com.pan.user.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.pan.commons.ServerResponse;
import com.pan.commons.SystemConstant;
import com.pan.user.common.Results;
import com.pan.user.dao.UserDao;
import com.pan.user.po.SysRole;
import com.pan.user.po.SysUser;
import com.pan.user.service.IUserService;
import com.pan.utils.RedisUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Random;

@Service
public class UserServiceImpl implements IUserService {

    @Resource
    private UserDao userDao;





    @Override
    public Results<SysUser> getAllUsersByPage(Integer startPosition, Integer limit) {
//        String userJsonCount = RedisUtil.get(SystemConstant.USERCOUNT_KEY);
//        String userJsonStr = RedisUtil.get(SystemConstant.USERLIST_KEY);
//        if(StringUtils.isNotBlank(userJsonCount) && StringUtils.isNotBlank(userJsonStr)){
//            List<SysUser> sysUsers = JSONObject.parseArray(userJsonStr, SysUser.class);
//            Long aLong = JSONObject.parseObject(userJsonCount, Long.class);
//            //续命
//            Random random = new Random();
//            int expire = SystemConstant.ROLELIST_KEY_EXPIRE + random.nextInt(100);
//            RedisUtil.expire(SystemConstant.USERLIST_KEY,expire);
//            RedisUtil.expire(SystemConstant.USERCOUNT_KEY,expire);
//            return Results.success(aLong.intValue(),sysUsers);
//        }
        Long userCount = userDao.countAllUsers();
        List<SysUser> allUsersByPage = userDao.getAllUsersByPage(startPosition, limit);
//        if(CollectionUtils.isNotEmpty(allUsersByPage) && StringUtils.isNotBlank(userCount.toString())){
//            userJsonStr = JSONObject.toJSONString(allUsersByPage);
//            userJsonCount = JSONObject.toJSONString(userCount.toString());
//            RedisUtil.setEx(SystemConstant.USERCOUNT_KEY,userJsonCount,SystemConstant.USERLIST_KEY_EXPIRE);
//            RedisUtil.setEx(SystemConstant.USERLIST_KEY,userJsonStr,SystemConstant.USERLIST_KEY_EXPIRE);
//        }else {
//            RedisUtil.setEx(SystemConstant.USERCOUNT_KEY,null,SystemConstant.EMPTY_KEY);
//            RedisUtil.setEx(SystemConstant.USERLIST_KEY,null,SystemConstant.EMPTY_KEY);
//        }
        return Results.success(userCount.intValue(),allUsersByPage);
    }
}
