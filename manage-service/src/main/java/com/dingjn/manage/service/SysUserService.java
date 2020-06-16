package com.dingjn.manage.service;

import com.dingjn.manage.model.dto.SysUserDTO;
import com.dingjn.manage.model.dto.SysUserRoleDTO;
import com.dingjn.manage.model.vo.SysUserVO;
import com.dingjn.manage.persistence.entity.SysUser;
import com.github.pagehelper.PageInfo;

import java.util.Map;

/**
 * @Auther: dingjn
 * @Desc:
 */
public interface SysUserService {

    SysUser getUserByUserName(String userName);


    PageInfo<SysUserVO> getUsers(SysUserDTO sysUserBO);

    void deleteUser(Integer userId);

    void saveUser(SysUser sysuser);

    Map<String, Object> getCheckedRoles(Integer userId);

    void saveRoles(SysUserRoleDTO sysUserRoleDTO);
}
