package com.pan.user.controller;

import com.pan.user.common.PageTableRequest;
import com.pan.user.common.Results;
import com.pan.user.po.SysUser;
import com.pan.user.service.IUserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("/user")
public class UserController {

    @Resource
    private IUserService userService;

    @GetMapping("/list")
    @ApiOperation(value = "分页获取用户信息", notes = "分页获取用户信息")
    @ApiImplicitParam(name = "request", value = "分页查询实体类", required=false)
    @ResponseBody
//    @PreAuthorize("hasAuthority('sys:user:query')")
    public Results<SysUser> getUsers(PageTableRequest request) {
        request.countOffset();
        return userService.getAllUsersByPage(request.getOffset(),request.getLimit());
    }

    @GetMapping(value = "/add")
//    @PreAuthorize("hasAuthority('sys:user:add')")
    @ApiOperation(value = "用户新增页面", notes = "跳转到新增用户信息页面")//描述
    public String addUser(Model model) {
        model.addAttribute("sysUser",new SysUser());
        return "user/user-add";
    }
}
