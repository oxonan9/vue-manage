package com.dingjn.manage.web.controller;

import com.dingjn.manage.common.response.ServerResponse;
import com.dingjn.manage.model.dto.SysUserDTO;
import com.dingjn.manage.model.dto.SysUserRoleDTO;
import com.dingjn.manage.model.vo.SysUserVO;
import com.dingjn.manage.persistence.entity.SysUser;
import com.dingjn.manage.service.SysUserService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Auther: dingjn
 * @Desc: 用户管理Controller
 */
@RestController
@RequestMapping("/sysuser")
public class SysUserController {

    @Autowired
    SysUserService sysUserService;

    @GetMapping("/query")
    public ServerResponse<PageInfo<SysUserVO>> getUsers(SysUserDTO sysUserBO) {
        return ServerResponse.success(sysUserService.getUsers(sysUserBO));
    }

    @PostMapping(value = "/add")
    public ServerResponse saveUser(@RequestBody SysUser sysUser) {
        sysUserService.saveUser(sysUser);
        return ServerResponse.success("保存用户成功!");
    }


    @PostMapping(value = "/delete")
    public ServerResponse delUser(@RequestParam Integer userId) {
        sysUserService.deleteUser(userId);
        return ServerResponse.success("删除用户成功!");
    }


    @GetMapping("/checkedroles")
    public ServerResponse currentRole(@RequestParam("userId") Integer userId) {
        return ServerResponse.success(sysUserService.getCheckedRoles(userId));
    }

    @PostMapping("/saveroles")
    public ServerResponse saveRoles(@RequestBody SysUserRoleDTO sysUserRoleDTO) {
        sysUserService.saveRoles(sysUserRoleDTO);
        return ServerResponse.success("分配角色成功!");
    }

}
