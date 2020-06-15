package com.dingjn.manage.web.controller;

import com.dingjn.manage.common.exception.CustomException;
import com.dingjn.manage.common.exception.CustomExceptionType;
import com.dingjn.manage.common.response.ServerResponse;
import com.dingjn.manage.persistence.entity.SysMenu;
import com.dingjn.manage.persistence.entity.SysOrg;
import com.dingjn.manage.service.SysMenuService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @Auther: dingjn
 * @Desc:
 */
@CrossOrigin
@RestController
public class SysMenuController {

    @Resource
    private SysMenuService sysMenuService;

    @PostMapping(value = "/getMenuTree")
    public ServerResponse tree(@RequestParam("menuNameLike") String menuNameLike) {
        return ServerResponse.success(sysMenuService.getMenuTree(menuNameLike, null));
    }


    @PostMapping(value = "/saveMenu")
    public ServerResponse saveOrg(@Valid @RequestBody SysMenu sysMenu) {
        sysMenuService.saveMenu(sysMenu);
        return ServerResponse.success("保存菜单信息成功！");
    }


    @PostMapping(value = "/delMenu")
    public ServerResponse delOrg(@RequestParam("id") Integer id,
                                 @RequestParam("menuPid") Integer menuPid) {
        if (id == null || menuPid == null) {
            throw new CustomException(CustomExceptionType.USER_INPUT_ERROR, "参数id和menuPid不能为空");
        }
        sysMenuService.delMenu(id, menuPid);
        return ServerResponse.success("删除菜单信息成功！");
    }
}
