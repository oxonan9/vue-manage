package com.dingjn.manage.service;

import com.dingjn.manage.model.dto.SysUserDTO;
import com.dingjn.manage.model.dto.SysUserRoleDTO;
import com.dingjn.manage.model.vo.SysUserVO;
import com.dingjn.manage.persistence.entity.SysUser;
import com.github.pagehelper.PageInfo;

import java.util.Map;

/**
 * @Auther: dingjn
 * @Desc: 用户管理Service
 */
public interface SysUserService {

    /**
     * 根据用户名获取.
     */
    SysUser getUserByUserName(String userName);

    /**
     * 根据前端传过来的条件获取.
     */
    PageInfo<SysUserVO> getUsers(SysUserDTO sysUserBO);

    /**
     * 删除.
     */
    void deleteUser(Integer userId);

    /**
     * 保存.
     */
    void saveUser(SysUser sysuser);

    /**
     * 获取当前用户的权限.
     */
    Map<String, Object> getCheckedRoles(Integer userId);

    /**
     * 保存当前用户的权限.
     */
    void saveRoles(SysUserRoleDTO sysUserRoleDTO);
}
