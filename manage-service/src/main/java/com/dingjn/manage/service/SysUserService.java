package com.dingjn.manage.service;

import com.dingjn.manage.model.bo.SysUserBO;
import com.dingjn.manage.model.vo.SysUserVO;
import com.dingjn.manage.persistence.entity.SysUser;

import java.util.List;

/**
 * @Auther: dingjn
 * @Desc:
 */
public interface SysUserService {

    SysUser getUserByUserName(String userName);


    List<SysUserVO> getUsers(SysUserBO sysUserBO);

    void deleteUser(Integer userId);

    void saveUser(SysUser sysuser);

}
