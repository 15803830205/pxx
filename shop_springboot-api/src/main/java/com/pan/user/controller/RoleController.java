package com.pan.user.controller;

import com.pan.user.common.Results;
import com.pan.user.service.IRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/role")
@Api(tags = "角色")
public class RoleController {

    @Autowired
    private IRoleService roleService;

    @GetMapping("/all")
    @ResponseBody
    @ApiOperation(value = "查询角色",notes = "获取所以角色信息")
    public Results getAllRole(){
        return roleService.getAllRole();
    }
}
