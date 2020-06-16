package com.dingjn.manage.web.controller;

import com.dingjn.manage.common.response.ServerResponse;
import com.dingjn.manage.model.vo.SysRoleVO;
import com.dingjn.manage.persistence.entity.SysRole;
import com.dingjn.manage.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Auther: dingjn
 * @Desc:
 */
@CrossOrigin
@RestController
@RequestMapping("/sysrole")
public class SysRoleController {

    @Autowired
    SysRoleService sysRoleService;

    @GetMapping("/query")
    public ServerResponse getRoles(@RequestParam("roleLike") String roleLike) {
        List<SysRole> sysRoleList = sysRoleService.getRoles(roleLike);
        return ServerResponse.success(sysRoleList);
    }

    @PostMapping("/add")
    public ServerResponse saveRole(@RequestBody SysRoleVO sysRoleVO) {
        sysRoleService.saveRole(sysRoleVO);
        return ServerResponse.success("保存角色成功!");
    }

    @PostMapping("/delete")
    public ServerResponse delRole(@RequestParam("id") Integer roleId) {
        sysRoleService.delRole(roleId);
        return ServerResponse.success("删除角色成功!");
    }
}
