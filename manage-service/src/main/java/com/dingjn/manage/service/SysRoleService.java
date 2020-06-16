package com.dingjn.manage.service;

import com.dingjn.manage.model.vo.SysRoleVO;
import com.dingjn.manage.persistence.entity.SysRole;

import java.util.List;

/**
 * @Auther: dingjn
 * @Desc: 角色服务层
 */
public interface SysRoleService {

    List<SysRole> getRoles(String roleLike);

    void delRole(Integer roleId);

    void saveRole(SysRoleVO sysRoleVO);

}
