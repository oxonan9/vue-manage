package com.dingjn.manage.web.controller;

import com.dingjn.manage.common.response.ServerResponse;
import com.dingjn.manage.persistence.entity.SysUser;
import com.dingjn.manage.service.SysOrgService;
import com.dingjn.manage.service.SysUserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Auther: dingjn
 * @Desc:
 */
@CrossOrigin
@RestController
public class SysOrgController {

    @Resource
    private SysOrgService sysOrgService;

    @Resource
    private SysUserService sysUserService;

    @PostMapping(value = "/tree")
    public ServerResponse tree(@RequestParam("username") String username) {
        SysUser sysUser = sysUserService.getUserByUserName(username);
        return ServerResponse.success(sysOrgService.getOrgTreeById(sysUser.getOrgId(), null, null));

    }
}
