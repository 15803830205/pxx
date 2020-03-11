package com.pan.user.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pan.user.po.SysRole;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RoleDao extends BaseMapper<SysRole> {
}
