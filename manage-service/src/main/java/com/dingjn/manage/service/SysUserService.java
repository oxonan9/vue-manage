package com.dingjn.manage.service;

import com.dingjn.manage.persistence.entity.SysUser;

/**
 * @Auther: dingjn
 * @Desc:
 */
public interface SysUserService {

    SysUser getUserByUserName(String userName);

}
