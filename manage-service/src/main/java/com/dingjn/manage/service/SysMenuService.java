package com.dingjn.manage.service;

import com.dingjn.manage.model.node.SysMenuNode;
import com.dingjn.manage.persistence.entity.SysMenu;

import java.util.List;

/**
 * @Auther: dingjn
 * @Desc:  菜单管理Service
 */
public interface SysMenuService {

    /**
     * 获取菜单全部信息 树状.
     */
    List<SysMenuNode> getMenuTree(String orgNameLike,
                                  Boolean orgStatus);


    /**
     * 新增/修改菜单.
     */
    void saveMenu(SysMenu sysMenu);

    /**
     * 删除菜单.
     */
    void delMenu(Integer id, Integer menuPid);

    /**
     * 获取默认展开的数据 返回id集合,
     */
    List<Integer> getDefauleExpandedKeys();

    /**
     * 获取当前角色默认具有的菜单 返回id集合.
     */
    List<Integer> getDefaultCheckedKeys(Integer roleId);

    /**
     * 保存角色的菜单权限.
     */
    void saveMenuPerm(Integer roleId, List<Integer> menuIds);

    /**
     * 根据用户名获取菜单信息 树状.
     */
    List<SysMenuNode> getMenuTreeByUsername(String username);
}