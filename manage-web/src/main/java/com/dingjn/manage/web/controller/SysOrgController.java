package com.dingjn.manage.web.controller;

import com.dingjn.manage.common.exception.CustomException;
import com.dingjn.manage.common.exception.CustomExceptionType;
import com.dingjn.manage.common.response.ServerResponse;
import com.dingjn.manage.persistence.entity.SysOrg;
import com.dingjn.manage.persistence.entity.SysUser;
import com.dingjn.manage.service.SysOrgService;
import com.dingjn.manage.service.SysUserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

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

    @PostMapping(value = "/getOrgTree")
    public ServerResponse tree(@RequestParam("username") String username,
                               @RequestParam("orgNameLike") String orgNameLike) {
        SysUser sysUser = sysUserService.getUserByUserName(username);
        return ServerResponse.success(sysOrgService.getOrgTreeById(sysUser.getOrgId(), orgNameLike, null));

    }

    @PostMapping(value = "/saveOrg")
    public ServerResponse saveOrg(@Valid @RequestBody SysOrg sysOrg) {
        sysOrgService.saveOrg(sysOrg);
        return ServerResponse.success("保存组织机构成功！");
    }


    @PostMapping(value = "/delOrg")
    public ServerResponse delOrg(@RequestParam("id") Integer id,
                                 @RequestParam("orgPid") Integer orgPid) {
        if (id == null || orgPid == null) {
            throw new CustomException(CustomExceptionType.USER_INPUT_ERROR, "参数id和orgPid不能为空");
        }
        sysOrgService.delOrg(id, orgPid);
        return ServerResponse.success("删除组织机构成功！");
    }
}
