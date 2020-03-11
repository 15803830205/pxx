package com.pan.user.service;

import com.pan.commons.ServerResponse;
import com.pan.user.common.Results;
import com.pan.user.po.SysUser;

public interface IUserService {





    Results<SysUser> getAllUsersByPage(Integer startPosition, Integer limit);
}
