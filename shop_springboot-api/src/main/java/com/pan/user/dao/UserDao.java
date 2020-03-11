package com.pan.user.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pan.user.po.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserDao extends BaseMapper<SysUser> {
    @Select("select count(*) from sys_user t ")
    Long countAllUsers();

    @Select("select * from sys_user t order by t.id limit #{startPosition},#{limit}")
    List<SysUser> getAllUsersByPage(@Param("startPosition")Integer startPosition, @Param("limit")Integer limit);
}
