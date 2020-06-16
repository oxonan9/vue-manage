package com.dingjn.manage.service;

import com.dingjn.manage.model.vo.SysRoleVO;
import com.dingjn.manage.persistence.entity.SysRole;

import java.util.List;

/**
 * @Auther: dingjn
 * @Desc: 角色服务Service
 */
public interface SysRoleService {

    /**
     * 获取角色信息.
     */
    List<SysRole> getRoles(String roleLike);

    /**
     * 删除角色.
     */
    void delRole(Integer roleId);

    /**
     * 保存角色.
     */
    void saveRole(SysRoleVO sysRoleVO);

}
