package com.dingjn.manage.service;

import com.dingjn.manage.model.SysMenuNode;
import com.dingjn.manage.model.SysOrgNode;
import com.dingjn.manage.persistence.entity.SysMenu;
import com.dingjn.manage.persistence.entity.SysOrg;

import java.util.List;

/**
 * @Auther: dingjn
 * @Desc:
 */
public interface SysMenuService {
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
}
