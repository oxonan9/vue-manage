package com.dingjn.manage.web.controller;

import com.dingjn.manage.common.exception.CustomException;
import com.dingjn.manage.common.exception.CustomExceptionType;
import com.dingjn.manage.common.response.ServerResponse;
import com.dingjn.manage.model.node.SysMenuNode;
import com.dingjn.manage.model.vo.PermVO;
import com.dingjn.manage.persistence.entity.SysMenu;
import com.dingjn.manage.service.SysMenuService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: dingjn
 * @Desc:
 */
@RestController
@RequestMapping("/sysmenu")
public class SysMenuController {

    @Resource
    private SysMenuService sysMenuService;

    @PostMapping(value = "/tree")
    public ServerResponse tree(@RequestParam("menuNameLike") String menuNameLike) {
        return ServerResponse.success(sysMenuService.getMenuTree(menuNameLike, null));
    }


    @PostMapping(value = "/add")
    public ServerResponse saveOrg(@Valid @RequestBody SysMenu sysMenu) {
        sysMenuService.saveMenu(sysMenu);
        return ServerResponse.success("保存菜单信息成功！");
    }


    @PostMapping(value = "/delete")
    public ServerResponse delOrg(@RequestParam("id") Integer id,
                                 @RequestParam("menuPid") Integer menuPid) {
        if (id == null || menuPid == null) {
            throw new CustomException(CustomExceptionType.USER_INPUT_ERROR, "参数id和menuPid不能为空");
        }
        sysMenuService.delMenu(id, menuPid);
        return ServerResponse.success("删除菜单信息成功！");
    }

    @PostMapping(value = "/checkedtree")
    public ServerResponse checkTree(@RequestParam("roleId") Integer roleId) {
        Map<String, Object> resultMap = new HashMap<>();
        // 1.获取全部的菜单树状数据
        resultMap.put("treeData", sysMenuService.getMenuTree(null, null));
        // 2.获取默认展开的数据 id
        resultMap.put("expandedKeys", sysMenuService.getDefauleExpandedKeys());
        // 3.获取默认勾选的数据，也就是当前角色具有的菜单数据
        resultMap.put("checkedKeys", sysMenuService.getDefaultCheckedKeys(roleId));
        return ServerResponse.success(resultMap);
    }

    /**
     * 保存菜单权限.
     */
    @PostMapping(value = "/savekeys")
    public ServerResponse<String> saveMenuPerm(@RequestBody PermVO permMenuVO) {
        sysMenuService.saveMenuPerm(permMenuVO.getRoleId(), permMenuVO.getCheckKeys());
        return ServerResponse.success("保存菜单权限成功!");
    }


    @PostMapping(value = "/tree/user")
    public ServerResponse usertree(@RequestParam("username") String username) {
        return ServerResponse.success(sysMenuService.getMenuTreeByUsername(username));
    }
}
